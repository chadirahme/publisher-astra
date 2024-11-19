package com.tacticals.publisherastra.controller;

import com.tacticals.publisherastra.config.DatadumbConfig;
import com.tacticals.publisherastra.model.Author;
import com.tacticals.publisherastra.model.Book;
import com.tacticals.publisherastra.repository.AuthorRepository;
import com.tacticals.publisherastra.repository.BookRepository;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@RestController
@RequestMapping("books")
public class BookController {

    @Autowired
    DatadumbConfig datadumbConfig;

    @Autowired
    AuthorRepository authorRepository;
    @Autowired
    BookRepository bookRepository;

    @GetMapping("/allBooks")
    public String getBooks() {
       //List<Book> list= bookRepository.findAll();
       // return "books > " + list.size();
        Author author=new Author();
        author.setId("1");
        author.setName("author1");
        //author.setAddress("address1");
        authorRepository.save(author);
        return "books > "+authorRepository.findAll().size();
    }

    @PostMapping("/loadData")
    public String loadData() {
        try {
            Path path = Paths.get(datadumbConfig.getBook());
            DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSSSSS");  // 2019-01-01T00:00:00.000000
            try(Stream<String> lines = java.nio.file.Files.lines(path)) {
                lines.limit(50).forEach(line->{
                    //System.out.println(line);
                    String json=line.substring(line.indexOf("{"));
                    //System.out.println(json);
                    try{
                        JSONObject jsonObject=new JSONObject(json);
                        Book book=new Book();
                        book.setId(jsonObject.optString("key").replace("/works/",""));
                        book.setName(jsonObject.optString("title"));
                        JSONObject descriptionObj= jsonObject.optJSONObject("description");
                        if(descriptionObj!=null){
                            book.setDescription(descriptionObj.optString("value"));
                        }

                        JSONObject createdObj= jsonObject.optJSONObject("created");
                        if(createdObj!=null){
                            String publishDate=createdObj.getString("value");
                           book.setPublishedDate(LocalDate.parse(publishDate,dateTimeFormatter));
                        }
                       JSONArray coversJson= jsonObject.optJSONArray("covers");
                        if(coversJson!=null){
                           List<String> coverIds= new ArrayList<>();
                            for (int i=0; i< coversJson.length(); i++){
                             coverIds.add(coversJson.getString(i));
                            }
                            book.setCoverIds(coverIds);
                        }

                        JSONArray authorJsonArray= jsonObject.optJSONArray("authors");
                        if(authorJsonArray!=null){
                            List<String> authorsIds= new ArrayList<>();
                            for (int i=0; i< authorJsonArray.length(); i++){
                                String authorId=authorJsonArray.getJSONObject(i).getJSONObject("author").getString("key")
                                        .replace("/authors/","");
                                authorsIds.add(authorId);
                            }
                            book.setAuthorIds(authorsIds);
                          List<String> authorNames=  authorsIds.stream().map(authorId->authorRepository.findById(authorId))
                                    .map(optinalAuthor->{
                                        if(!optinalAuthor.isPresent())return "Unknown author";
                                        return optinalAuthor.get().getName();
                                    }).collect(Collectors.toList());
                          book.setAuthorNames(authorNames);

                        }
                        System.out.println("saving book "+book.getName() + " ...");
                       bookRepository.save(book);
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
