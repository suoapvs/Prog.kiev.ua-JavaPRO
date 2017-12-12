package com.photos;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Controller
@RequestMapping("/")
public final class MyController {

    private final Map<Long, byte[]> photos = new ConcurrentHashMap<>();

    @RequestMapping(value = {"", "index", "home"}, method = RequestMethod.GET)
    public String onIndex() {
        return "index";
    }

    @RequestMapping(value = "/add_photo", method = RequestMethod.POST)
    public String onAddPhoto(
            final Model model,
            @RequestParam final MultipartFile photo
    ) throws PhotoErrorException {
        if (photo.isEmpty()) {
            throw new PhotoErrorException();
        }
        try {
            final long id = System.currentTimeMillis();
            this.photos.put(id, photo.getBytes());
            model.addAttribute("photo_id", id);
            return "result";
        } catch (IOException e) {
            throw new PhotoErrorException();
        }
    }

    @RequestMapping("/photo/{photo_id}")
    public ResponseEntity<byte[]> onPhoto(@PathVariable("photo_id") final long id) {
        return photoById(id);
    }

    @RequestMapping(value = "/view", method = RequestMethod.POST)
    public ResponseEntity<byte[]> onView(@RequestParam("photo_id") final long id) {
        return photoById(id);
    }

    @RequestMapping("/delete/{photo_id}")
    public String onDelete(@PathVariable("photo_id") final long id) throws PhotoNotFoundException {
        if (this.photos.remove(id) == null) {
            throw new PhotoNotFoundException();
        } else {
            return "index";
        }
    }

    @RequestMapping(value = "/all_photos", method = RequestMethod.GET)
    public String showAll(final Model model) {
        model.addAttribute("mapIsNotEmpty", !this.photos.isEmpty());
        model.addAttribute("photo_ids", this.photos.keySet());
        return "index";
    }

    @RequestMapping(value = "/all_photos", method = RequestMethod.POST)
    public String onDeleteSelect(@RequestParam("photo_ids") final long[] photoIds) {
        for (long id : photoIds) {
            this.photos.remove(id);
        }
        return "index";
    }

    private ResponseEntity<byte[]> photoById(final long id) throws PhotoNotFoundException {
        byte[] bytes = this.photos.get(id);
        if (bytes == null) {
            throw new PhotoNotFoundException();
        }
        final HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.IMAGE_PNG);
        return new ResponseEntity<>(bytes, headers, HttpStatus.OK);
    }
}