package com.sgbg.api.controller;

import com.sgbg.api.request.RoomReq;
import com.sgbg.api.response.BaseResponseBody;
import com.sgbg.api.response.RoomListRes;
import com.sgbg.api.response.RoomRes;
import com.sgbg.service.RoomService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import okhttp3.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Tag(name = "Room API", description = "방 생성, 카테고리별 방 목록 조회, 방 상세정보 조회 기능 제공")
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


    @Operation(summary = "방 생성 메서드")
    @Parameters()
    @PostMapping("/create")
    public ResponseEntity createRoom(@RequestBody RoomReq roomReq ,HttpServletRequest request) {
        System.out.println("000000");
        roomService.createRoom(roomReq);

        return ResponseEntity.status(200).body(BaseResponseBody.of(2010,"Accepted"));
    }

    // 요청 url = /room?page=pageNum&size=pageSize&sort=desc
    @Operation(summary = "전체 방 목록 조회 메서드")
    @ApiResponses(
            @ApiResponse(responseCode = "2000", description = "방 목록 조회 성공",
            content = @Content(schema = @Schema(implementation = RoomListRes.class)))
    )
    @GetMapping("")
    public ResponseEntity<? extends RoomListRes> getRoomList(Pageable pageable){
        List<RoomRes> roomResList = roomService.getRoomList(pageable);
        return ResponseEntity.status(200).body(RoomListRes.of(2000,"Success",roomResList));
    }


    @Operation(summary = "방 상세정보 조회 메서드")
    @ApiResponses(
            @ApiResponse(responseCode = "2000", description = "방 상세정보 조회 성공",
            content = @Content(schema = @Schema(implementation = RoomRes.class)))
    )
    @GetMapping("/{roomId}")
    public ResponseEntity<? extends RoomRes> getRoom(@PathVariable Long roomId) {

        RoomRes roomRes = roomService.getRoom(roomId);
        System.out.println(roomRes);



        return ResponseEntity.status(200).body(roomRes.roomResEntity(2000,"Success",roomRes));

    }

    @Operation(summary = "대분류 별 방 목록 조회")
    @ApiResponses(
            @ApiResponse(responseCode = "2000", description = "대분류 별 방 목록 조회 성공",
                    content = @Content(schema = @Schema(implementation = RoomListRes.class)))
    )
    @GetMapping("/category/{parentCategory}")
    public ResponseEntity<? extends RoomListRes> getRoomListCategory(@PathVariable String parentCategory, Pageable pageable){
        System.out.println("-----");
        System.out.println(parentCategory);
        System.out.println("-----");
        List<RoomRes> roomResList = roomService.getRoomList(parentCategory, pageable);
        return ResponseEntity.status(200).body(RoomListRes.of(2000,"Success",roomResList));
    }


}
