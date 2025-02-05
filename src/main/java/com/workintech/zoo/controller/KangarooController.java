package com.workintech.zoo.controller;

import com.workintech.zoo.entity.Kangaroo;
import com.workintech.zoo.exceptions.ZooException;
import jakarta.annotation.PostConstruct;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/kangaroos")
public class KangarooController {

    private Map<Integer, Kangaroo> kangaroos;

    @PostConstruct
    public void init()
    {
        kangaroos = new HashMap<>();
    }

    @GetMapping()
    public List<Kangaroo> findAll()
    {
        return kangaroos.values().stream().toList();
    }

    @GetMapping("/{id}")
    public Kangaroo findById(@PathVariable int id)
    {
        if(id <= 0)
        {
            throw new ZooException("Id must be greater than zoo", HttpStatus.BAD_REQUEST);
        }

        if(!kangaroos.containsKey(id))
        {
            throw new ZooException("Kangaroo id is not exist: " + id, HttpStatus.NOT_FOUND);
        }
        return kangaroos.get(id);
    }

    @PostMapping
    public Kangaroo save(@RequestBody Kangaroo kangaroo)
    {
        if(kangaroo.getId() == null)
        {
            throw new ZooException("Kangaroo instance is not null!!",HttpStatus.BAD_REQUEST);
        }
        kangaroos.put(kangaroo.getId(),kangaroo);
        return kangaroos.get(kangaroo.getId());
    }

    @PutMapping("/{id}")
    public Kangaroo update(@PathVariable int id, @RequestBody Kangaroo kangaroo)
    {
        if(id <= 0)
        {
            throw new ZooException("Id must be greater than zoo", HttpStatus.BAD_REQUEST);
        }

        if(!kangaroos.containsKey(id))
        {
            throw new ZooException("Kangaroo id is not exist: " + id, HttpStatus.NOT_FOUND);
        }
        kangaroos.put(id,kangaroo);
        return kangaroo;
    }

    @DeleteMapping("/{id}")
    public Kangaroo delete(@PathVariable int id)
    {
        if(id <= 0)
        {
            throw new ZooException("Id must be greater than zoo", HttpStatus.BAD_REQUEST);
        }

        if(!kangaroos.containsKey(id))
        {
            throw new ZooException("Kangaroo id is not exist: " + id, HttpStatus.NOT_FOUND);
        }
        Kangaroo kangaroo = kangaroos.get(id);
        kangaroos.remove(kangaroo.getId());
        return kangaroo;
    }
}
