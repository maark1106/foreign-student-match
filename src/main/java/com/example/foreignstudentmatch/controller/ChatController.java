package com.example.foreignstudentmatch.controller;

import com.example.foreignstudentmatch.annotation.StudentId;
import com.example.foreignstudentmatch.dto.ResponseDto;
import com.example.foreignstudentmatch.service.ChatService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/chat")
@RequiredArgsConstructor
public class ChatController {

    private final ChatService chatService;

    @GetMapping
    public ResponseDto<?> readChatRooms(@StudentId Long studentId){
        return new ResponseDto<>(chatService.readChatRooms(studentId));
    }

    @GetMapping("/{chat_room_id}")
    public ResponseDto<?> readChatMessages(@PathVariable("chat_room_id") Long chatRoomId, @StudentId Long studentId){
        return new ResponseDto<>(chatService.readChatMessages(chatRoomId, studentId));
    }

    @DeleteMapping("/{chat_room_id}")
    public ResponseDto<?> exitChatRoom(@PathVariable("chat_room_id") Long chatRoomId, @StudentId Long studentId){
        return new ResponseDto<>(chatService.exitChatRoom(chatRoomId, studentId));
    }
}
