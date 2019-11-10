package com.unn.serverNetwork.service.api;

import com.unn.serverNetwork.model.Interface;
import com.unn.serverNetwork.model.Link;
import com.unn.serverNetwork.model.NetworkElement;

import java.io.IOException;
import java.util.List;

public interface InventoryService {
    List<NetworkElement> getNetworkElements();

    NetworkElement storeNetworkElement(NetworkElement networkElement);

    NetworkElement getNetworkElementById(String networkElementId);

    NetworkElement removeNetworkElementById(String id);

    List<Interface> getInterfaces(String networkElementId);

    Interface storeInterface(String networkElementId, Interface inter);

    List<Link> getLinks(String networkElementId);

    Link storeLink(Link link);

    Link deleteLink(String id);

    List<Link> getAllLinks();

    Interface deleteInterfaces(String id);

    String storeData(String data);
}

