package me.gking2224.mstemplate.web.mvc;

import static java.lang.String.format;
import static java.util.stream.Collectors.toList;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

import java.time.format.DateTimeFormatter;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import me.gking2224.common.utils.JsonUtil;
import me.gking2224.mstemplate.model.Thing;
import me.gking2224.mstemplate.service.ThingService;

@RestController
@RequestMapping("/things")

public class ThingController {

    @SuppressWarnings("unused")
    private static Logger logger = LoggerFactory.getLogger(ThingController.class);

	@Autowired
	ThingService thingService;
	
	@Autowired  @Qualifier("longDateTimeFormat") DateTimeFormatter dateTimeFormatter;

	@Autowired
	JsonUtil jsonUtil;

    @RequestMapping(value="", method=RequestMethod.GET)
    public ResponseEntity<List<Thing>> getAllThings(
    ) {
        List<Thing> findAllThings = thingService.findAll();
        List<Thing> b = findAllThings.stream().map(this::enrichThing).collect(toList());

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(APPLICATION_JSON);
        return new ResponseEntity<List<Thing>>(b, headers, HttpStatus.OK);
    }

    @RequestMapping(value="", method=RequestMethod.POST, consumes=APPLICATION_JSON_VALUE)
    public ResponseEntity<Thing> newThing(
            @RequestBody Thing thing) {

        Thing b = thingService.create(thing);
        b = enrichThing(b);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        return new ResponseEntity<Thing>(b, headers, HttpStatus.OK);
    }

    @RequestMapping(value="/{id}", method=RequestMethod.PUT, consumes=APPLICATION_JSON_VALUE)
    public ResponseEntity<Thing> updateThing(
            @PathVariable("id") final Long id,
            @RequestBody final Thing thing) {
        if (thing.getId() == null) thing.setId(id);
        else if (!thing.getId().equals(id))
            throw new IllegalArgumentException("Illegal attempt to change immutable field (id)");
        Thing p = thingService.update(thing);
        p = enrichThing(p);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        return new ResponseEntity<Thing>(p, headers, HttpStatus.OK);
    }

    @RequestMapping(value="/{id}", method=RequestMethod.DELETE, consumes=APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> deleteThing(
            @PathVariable("id") final Long id) {

        thingService.delete(id);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        return new ResponseEntity<Void>(headers, HttpStatus.OK);
    }

    @RequestMapping(value="/{id}", method=RequestMethod.GET)
    public ResponseEntity<Thing> getThing(
            @PathVariable("id") final Long id) {
        Thing b = thingService.findById(id);
        b = enrichThing(b);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        return new ResponseEntity<Thing>(b, HttpStatus.OK);
    }

    private Thing enrichThing(Thing b) {
        b.setLocation(format("/things/%s", b.getId()));
        return b;
    }
}
