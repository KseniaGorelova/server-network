package com.unn.serverNetwork.service.impl;

import com.unn.serverNetwork.exception.ObjectNotFoundException;
import com.unn.serverNetwork.model.Interface;
import com.unn.serverNetwork.model.Link;
import com.unn.serverNetwork.model.NeToInterface;
import com.unn.serverNetwork.model.NetworkElement;
import com.unn.serverNetwork.model.repository.InterfaceRepository;
import com.unn.serverNetwork.model.repository.LinkRepository;
import com.unn.serverNetwork.model.repository.NeRepository;
import com.unn.serverNetwork.model.repository.NeToIntrRepository;
import com.unn.serverNetwork.service.api.InventoryService;
import lombok.RequiredArgsConstructor;
import com.google.common.collect.Lists;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.unn.serverNetwork.model.CollectionsNames.*;

@Service
@RequiredArgsConstructor
public class InventoryServiceImpl implements InventoryService {

    private final InterfaceRepository interfaceRepository;
    private final NeRepository neRepository;
    private final NeToIntrRepository neToInterRepository;
    private final LinkRepository linkRepository;

    @Override
    public List<NetworkElement> getNetworkElements() {
        return Lists.newArrayList(neRepository.findAll());
    }

    @Override
    public NetworkElement storeNetworkElement(NetworkElement networkElement) {
        return neRepository.save(networkElement);
    }

    @Override
    public NetworkElement getNetworkElementById(String networkElementId) {
        return findNeById(networkElementId);
    }

    @Override
    public NetworkElement removeNetworkElementById(String id) {
        NetworkElement networkElement = findNeById(id);

        List<Interface>  interfaces = networkElement.getInterfaces();
        interfaces.forEach(inter -> {
            deleteInterfaces(inter.getId());
        });

        neRepository.delete(networkElement);
        return networkElement;
    }

    @Override
    public List<Interface> getInterfaces(String networkElementId) {
        String neId = getID(NETWORK_ELEMENT, networkElementId);
        return interfaceRepository.findInterfacesByNeId(neId).asListRemaining();
    }

    @Override
    public Interface storeInterface(String networkElementId, Interface inter) {
        NetworkElement networkElement = neRepository.findById(networkElementId).orElseThrow(
                ()-> new ObjectNotFoundException("Unable to find network element with id: " + networkElementId ));

        if(networkElement.getInterfaces().contains(inter)){
            throw new IllegalStateException
                    ("Network element with id: " + networkElementId + " already has assigned interface");
        }

        Interface anInterface = interfaceRepository.save(inter);

        NeToInterface neToInterface = new NeToInterface(networkElement, anInterface);

        neToInterRepository.save(neToInterface);

        return anInterface;
    }

    @Override
    public List<Link> getLinks(String networkElementId) {
        String neName = findNeById(networkElementId).getName();
        return  linkRepository.findAllByNeName(neName).asListRemaining();
    }

    @Override
    public Link storeLink(Link link) {
        String aNeName = link.getNeAName();
        String zNeName = link.getNeZName();
        String aIntName = link.getInterAName();
        String zIntName = link.getInterZName();

        linkRepository.findLinkByEndPoints(aNeName, zNeName, aIntName, zIntName).asListRemaining()
                .stream().findFirst()
                .ifPresent(existedLink->{
                    throw new IllegalStateException("Link between " + aNeName + "|" + aIntName +
                            " and " + zNeName + "|" + zIntName + " already present.");
                });

        NetworkElement networkElementA = findNeByName(aNeName);
        NetworkElement networkElementZ = findNeByName(zNeName);
        Interface aInter = findInterByNeIdAndName(getID(NETWORK_ELEMENT,networkElementA.getId()), aIntName);
        Interface zInter = findInterByNeIdAndName(getID(NETWORK_ELEMENT, networkElementZ.getId()), zIntName);



        return linkRepository.save(Link.builder()
                .neAName(aNeName).neZName(zNeName)
                .interfaceA(aInter).interfaceZ(zInter)
                .interAName(aIntName).interZName(zIntName).build());
    }

    @Override
    public Link deleteLink(String id) {
        String idLink = getID(LINK, id);
        Link link = linkRepository.findById(idLink)
                .orElseThrow(()-> new ObjectNotFoundException
                        ("Unable to find link with id: " + id));
        linkRepository.delete(link);
        return link;
    }

    @Override
    public List<Link> getAllLinks() {
        return Lists.newArrayList(linkRepository.findAll());
    }

    @Override
    public Interface deleteInterfaces(String id) {
        String idInterface = getID(INTERFACE, id);
        Interface inter = interfaceRepository.findById(idInterface)
                .orElseThrow(()->new ObjectNotFoundException
                        ("Unable to find interface with id :" + id));

        NeToInterface neToInt =  neToInterRepository.findNeIdByInterID(idInterface).asListRemaining()
                .stream().findFirst().orElseThrow(()-> new ObjectNotFoundException(("Unabled to find")));

        String neName = neToInt.getNe().getName();
        List<Link> links = linkRepository.findAllByNeNameAndInterName(neName, inter.getName()).asListRemaining();
        links.forEach(l->{deleteLink(l.getId());});
        neToInterRepository.removeNeToInterfaceByIntId(idInterface);

        interfaceRepository.delete(inter);

        return  inter;
    }

    @Override
    public String storeData(String data){
        return null;
    }

    private NetworkElement findNeById(String networkElementId) {

        return  neRepository.findById(networkElementId).orElseThrow(()-> new ObjectNotFoundException
                ("Unable to find network element with id : " + networkElementId ));
    }

    private static String getID(String collection, String key){
        return collection + "/" + key;
    }

    private NetworkElement findNeByName(String neName) {
        return neRepository.findByName(neName)
                .asListRemaining().stream().findFirst()
                .orElseThrow(()-> new ObjectNotFoundException
                        ("Unable to find network element with name : " + neName ));
    }

    private Interface findInterByNeIdAndName(String id, String intName) {
        return interfaceRepository.findInterfaceByNeIdAndName(id,intName)
                .asListRemaining().stream().findFirst()
                .orElseThrow(()->new ObjectNotFoundException
                        ("Unable to find interface with name: " + intName));
    }
}
