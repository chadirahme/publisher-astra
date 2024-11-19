package com.tacticals.publisherastra.controller;

import com.tacticals.publisherastra.config.DatadumbConfig;
import com.tacticals.publisherastra.model.Author;
import com.tacticals.publisherastra.repository.AuthorRepository;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Stream;

@RestController
@RequestMapping("authors")
public class AuthorController {

    @Autowired
    DatadumbConfig datadumbConfig;

    @Autowired
    AuthorRepository authorRepository;

    @PostMapping("/loadData")
    public String loadData() {
        try {
            Path path = Paths.get(datadumbConfig.getAuthor());
            try(Stream<String> lines = java.nio.file.Files.lines(path)) {
                lines.limit(50).forEach(line->{
                    //System.out.println(line);
                    String json=line.substring(line.indexOf("{"));
                    //System.out.println(json);
                    try{
                        JSONObject jsonObject=new JSONObject(json);
                        Author author=new Author();
                        author.setId(jsonObject.optString("key").replace("/authors/",""));
                        author.setName(jsonObject.optString("name"));
                        author.setPersonalName(jsonObject.optString("personal_name"));
                        System.out.println("saving author "+author.getName() + " ...");
                        authorRepository.save(author);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                });
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return "Data loaded successfully";
    }

}
