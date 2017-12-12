package com.archive;

import org.springframework.core.io.FileSystemResource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

@Controller
@RequestMapping("/")
public final class AppController {

    @RequestMapping(value = {"", "index", "home"}, method = RequestMethod.GET)
    public String onIndex() {
        return "index";
    }

    @RequestMapping(value = "/zip", method = RequestMethod.POST)
    @ResponseBody
    public FileSystemResource zip(
            final HttpServletResponse response,
            @RequestParam("file") final MultipartFile file
    ) {
        String name = file.getOriginalFilename();
        name = name.substring(0, name.lastIndexOf(".")) + ".zip";
        response.setHeader("Content-Disposition", "attachment;filename=" + name);
        final File tempFile = compress(file);
        return new FileSystemResource(tempFile);
    }

    private static File compress(final MultipartFile file) {
        Path tempFile = null;
        try {
            tempFile = Files.createTempFile("", ".zip");
            try (FileOutputStream fos = new FileOutputStream(tempFile.toFile());
                 ZipOutputStream zos = new ZipOutputStream(fos)) {
                final ZipEntry entry = new ZipEntry(file.getOriginalFilename());
                zos.putNextEntry(entry);
                zos.write(file.getBytes());
                zos.closeEntry();
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return tempFile.toFile();
    }
}
