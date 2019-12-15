package com.unn.serverNetwork.controller;

import com.unn.serverNetwork.model.Interface;
import com.unn.serverNetwork.model.Link;
import com.unn.serverNetwork.model.NetworkElement;
import com.unn.serverNetwork.model.Route;
import com.unn.serverNetwork.service.api.InventoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/data")
@RequiredArgsConstructor
public class InventoryController {

    private final InventoryService inventoryService;

    @GetMapping("/interfaces/{id}")
    public Iterable<Interface> getInterfaces(@PathVariable String id) {
        return inventoryService.getInterfaces(id);
    }

    @PostMapping("/interfaces/{id}")
    public Interface createInterface(@PathVariable String id, @RequestBody Interface inter ){
        return inventoryService.storeInterface(id, inter);
    }

    @DeleteMapping("/interfaces/{id}")
    public Interface deleteInterfaces(@PathVariable String id) {
        return inventoryService.deleteInterfaces(id);
    }

    @GetMapping("/links/{id}")
    public List<Link> getLinkById(@PathVariable String id) {
        return inventoryService.getLinks(id);
    }

    @PostMapping("/links")
    public Link createLink(@RequestBody Link ln){
        return inventoryService.storeLink(ln);
    }

    @GetMapping("/links")
    public List<Link> getLink(){
        return inventoryService.getAllLinks();
    }

    @DeleteMapping("/links/{id}")
    public Link deleteLink(@PathVariable String id ){
        return inventoryService.deleteLink(id);
    }

    @GetMapping("/network-elements")
    public List<NetworkElement> getNetElements(){
        return inventoryService.getNetworkElements();
    }

    @DeleteMapping("/network-elements/{id}")
    public  NetworkElement delNetElement(@PathVariable String id) {
        return inventoryService.removeNetworkElementById(id);
    }

    @GetMapping("/network-elements/{id}")
    public NetworkElement getNetElementById(@PathVariable String id) {
        return inventoryService.getNetworkElementById(id);
    }

    @PostMapping("/network-elements")
    public NetworkElement createNetworkElement(@RequestBody NetworkElement networkEl) {
        return inventoryService.storeNetworkElement(networkEl);
    }

    @PostMapping
    public String storeData(@RequestBody String data) {
        return inventoryService.storeData(data);
    }

    @GetMapping("route/{id}")
    public List<Route> getRoutes(@PathVariable String id){
        return inventoryService.getRoutes(id);
    }
}
