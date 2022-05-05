// package com.example.controller;

// import java.util.List;
// import java.util.Optional;

// import com.example.model.Media;
// import com.example.service.MediaService;

// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.http.HttpStatus;
// import org.springframework.web.bind.annotation.CrossOrigin;
// import org.springframework.web.bind.annotation.DeleteMapping;
// import org.springframework.web.bind.annotation.GetMapping;
// import org.springframework.web.bind.annotation.PathVariable;
// import org.springframework.web.bind.annotation.PostMapping;
// import org.springframework.web.bind.annotation.RequestBody;
// import org.springframework.web.bind.annotation.RestController;
// import org.springframework.web.server.ResponseStatusException;
// import org.springframework.web.bind.annotation.RequestMapping;
// import org.springframework.web.bind.annotation.PutMapping;
// //

// @RestController
// @CrossOrigin
// @RequestMapping(value="/api/media")
// public class MediaControleur {

// @Autowired
// private MediaService mediaService;

// @GetMapping("/")
// public List<Media> allItems(){
// return mediaService.getAllMedia();
// }

// @GetMapping("/{itemId}")
// public Media read(@PathVariable(value = "itemId") int id){
// Optional<Media> media = mediaService.getMedia(id);

// if(!media.isPresent()){
// throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Aucun média n'existe
// à cet id");
// }

// return media.get();
// }

// @PostMapping("/create")
// public Media create(int id, @RequestBody Media media){
// return mediaService.saveMedia(media);
// }

// @PutMapping(value="/{idmedia}")
// public Media update(@PathVariable(value = "idmedia") int id, @RequestBody
// Media media) {

// if(id != media.getId()){
// throw new ResponseStatusException(HttpStatus.PRECONDITION_FAILED, "l'id du
// media passé en paramètre est différent de celui saisi.");
// }

// Optional<Media> mediaTMP = mediaService.getMedia(id);

// if(!mediaTMP.isPresent()){
// throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Aucun média n'existe
// à cet id");
// }

// Media newmedia = mediaTMP.get();
// newmedia.setRang(media.getRang());
// newmedia.setLabel(media.getLabel());

// return mediaService.saveMedia(newmedia);
// }

// @DeleteMapping("/{idmedia}")
// public void delete(@PathVariable(value = "idmedia") int id){
// mediaService.deleteMedia(id);
// }

// }
