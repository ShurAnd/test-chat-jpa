package org.andrey.testchatjpa.controller;

import java.net.URI;
import java.util.List;

import org.andrey.testchatjpa.domain.Message;
import org.andrey.testchatjpa.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
@RequestMapping("/messages")
public class MessageController {

	private MessageService messageService;
	
	@Autowired
	public MessageController(MessageService messageService) {
		this.messageService = messageService;
	}
	
	@GetMapping
	public ResponseEntity<List<Message>> getAllMessages(){
		List<Message> messages = messageService.getAllMessages();
		
		return ResponseEntity.ok(messages);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Message> getMessageById(@PathVariable("id") Long id){
		Message message = messageService.findMessageById(id);
		
		return ResponseEntity.ok(message);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Message> updateMessage(@PathVariable("id") Long id, @RequestBody Message message){
		Message updatedMessage = messageService.updateMessage(message);
		
		return ResponseEntity.ok(updatedMessage);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteMessageById(@PathVariable("id") Long id) {
		messageService.deleteMessageById(id);
		
		return ResponseEntity.noContent().build();
	}
	
	@PostMapping
	public ResponseEntity<?> createNewMessage(@RequestBody Message message, ServletUriComponentsBuilder uriBuilder){
		System.out.println("POST_MAPPING got message " + message);
		message = messageService.saveNewMessage(message);
		System.out.println("Message saved! " + message);
		URI uri = uriBuilder.fromCurrentRequestUri().path("/"+ message.getId()).build().toUri();
		System.out.println("====" + uri.toString() + "====");
		return ResponseEntity.created(uri).body(message);
	}
}
