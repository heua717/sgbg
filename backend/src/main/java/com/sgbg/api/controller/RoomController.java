package com.sgbg.api.controller;

import com.sgbg.api.request.RoomReq;
import com.sgbg.api.response.BaseResponseBody;
import com.sgbg.api.response.RoomRes;
import com.sgbg.service.RoomService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/room")
@RequiredArgsConstructor
public class RoomController {

    @Autowired
    RoomService roomService;

//    @Autowired
//    AuthService authService;

//    @Autowired
//    CookieUtil cookieUtil;


    @PostMapping("/create")
    public ResponseEntity createRoom(@RequestBody RoomReq roomReq ,HttpServletRequest request) {
        System.out.println("000000");
        roomService.createRoom(roomReq);

        return ResponseEntity.status(200).body(BaseResponseBody.of(201,"Accepted"));
    }

    // 요청 url = /room?page=pageNum&size=pageSize&sort=desc
    @GetMapping("")
    public ResponseEntity<List<RoomRes>> getRoomList(Pageable pageable){
        List<RoomRes> roomResList = roomService.getRoomList(pageable);
        return ResponseEntity.status(200).body(RoomRes.roomListResEntity(200,"Success",roomResList));
    }


    @GetMapping("/{roomId}")
    public ResponseEntity<? extends RoomRes> getRoom(@PathVariable Long roomId) {
        Map<String, Object> resultMap = new HashMap<>();

        RoomRes roomRes = roomService.getRoom(roomId);
        System.out.println(roomRes);



        return ResponseEntity.status(200).body(roomRes.roomResEntity(200,"Success",roomRes));

    }

    @GetMapping("/{parentCategory")
    public ResponseEntity<List<RoomRes>> getRoomListCategory(@PathVariable String parentCategory, Pageable pageable){
        return null;
    }


}
