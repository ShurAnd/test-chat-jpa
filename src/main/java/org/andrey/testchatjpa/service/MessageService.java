package org.andrey.testchatjpa.service;

import java.util.List;

import org.andrey.testchatjpa.domain.Message;
import org.andrey.testchatjpa.repository.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MessageService {

	private MessageRepository messageRepository;

	@Autowired
	public MessageService(MessageRepository messageRepository) {
		this.messageRepository = messageRepository;
	}

	public List<Message> getAllMessages() {
		List<Message> messages = messageRepository.findAll();

		return messages;
	}

	public Message findMessageById(Long id) {
		Message message = messageRepository.findById(id).orElse(null);

		return message;
	}

	public Message saveNewMessage(Message message) {
		return messageRepository.save(message);
	}

	public Message updateMessage(Message message) {
		/*
		 * так ли
		 */
		return saveNewMessage(message);
	}

	public void deleteMessageById(Long id) {
		messageRepository.deleteById(id);
	}
}
