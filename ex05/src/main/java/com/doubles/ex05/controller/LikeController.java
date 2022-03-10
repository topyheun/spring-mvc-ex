package com.doubles.ex05.controller;

import com.doubles.ex05.domain.BoardLikeVO;
import com.doubles.ex05.domain.ReplyLikeVO;
import com.doubles.ex05.service.LikeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.inject.Inject;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/like")
public class LikeController {

    @Inject
    private LikeService likeService;

    // 게시글 추천 하기
    @RequestMapping(value = "/create/{bno}/{uid}", method = RequestMethod.POST)
    public ResponseEntity<String> createBoardLike(@PathVariable("bno") Integer bno,
                                                  @PathVariable("uid") String uid) {
        ResponseEntity<String> entity = null;
        try {
            BoardLikeVO boardLikeVO = new BoardLikeVO();
            boardLikeVO.setBno(bno);
            boardLikeVO.setUid(uid);
            likeService.createBoardLike(boardLikeVO);
            entity = new ResponseEntity<>("BOARD LIKE CREATED", HttpStatus.OK);
        } catch (Exception e) {
            entity = new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
        return entity;
    }

    // 게시글 추천 취소
    @RequestMapping(value = "/remove/{bno}/{uid}", method = RequestMethod.DELETE)
    public ResponseEntity<String> removeBoardLike(@PathVariable("bno") Integer bno,
                                                  @PathVariable("uid") String uid) {
        ResponseEntity<String> entity = null;
        try {
            BoardLikeVO boardLikeVO = new BoardLikeVO();
            boardLikeVO.setBno(bno);
            boardLikeVO.setUid(uid);
            likeService.removeBoardLike(boardLikeVO);
            entity = new ResponseEntity<>("BOARD LIKE REMOVED", HttpStatus.OK);
        } catch (Exception e) {
            entity = new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
        return entity;
    }

    // 게시글 추천 갯수
    @RequestMapping(value = "/count/{bno}", method = RequestMethod.GET)
    public ResponseEntity<Map<String, Object>> countBoardLikes(@PathVariable("bno") Integer bno) {
        ResponseEntity<Map<String, Object>> entity = null;
        try {
            int likeTotalCount = likeService.countBoardLikes(bno);
            Map<String, Object> map = new HashMap<>();
            map.put("likeTotalCount", likeTotalCount);
            entity = new ResponseEntity<>(map, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            entity = new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return entity;
    }

    // 게시글 추천 여부
    @RequestMapping(value = "/check/{bno}/{uid}", method = RequestMethod.GET)
    public ResponseEntity<Map<String, Object>> checkBoardLike(@PathVariable("bno") Integer bno,
                                                              @PathVariable("uid") String uid) {
        ResponseEntity<Map<String, Object>> entity = null;
        try {
            boolean checkBoardLike = likeService.checkBoardLike(bno, uid);
            Map<String, Object> map = new HashMap<>();
            map.put("checkBoardLike", checkBoardLike);
            entity = new ResponseEntity<>(map, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            entity = new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return entity;
    }

    // 댓글 추천 하기
    @RequestMapping(value = "/create/{bno}/{uid}/{rno}", method = RequestMethod.POST)
    public ResponseEntity<String> createReplyLike(@PathVariable("bno") Integer bno,
                                                  @PathVariable("rno") Integer rno,
                                                  @PathVariable("uid") String uid) {
        ResponseEntity<String> entity = null;
        try {
            ReplyLikeVO replyLikeVO = new ReplyLikeVO();
            replyLikeVO.setBno(bno);
            replyLikeVO.setRno(rno);
            replyLikeVO.setUid(uid);
            likeService.createReplyLike(replyLikeVO);
            entity = new ResponseEntity<>("REPLY LIKE CREATED", HttpStatus.OK);
        } catch (Exception e) {
            entity = new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
        return entity;
    }

    // 댓글 추천 취소
    @RequestMapping(value = "/delete/{bno}/{uid}/{rno}", method = RequestMethod.DELETE)
    public ResponseEntity<String> deleteReplyLike(@PathVariable("bno") Integer bno,
                                                  @PathVariable("rno") Integer rno,
                                                  @PathVariable("uid") String uid) {

        ResponseEntity<String> entity = null;
        try {
            ReplyLikeVO replyLikeVO = new ReplyLikeVO();
            replyLikeVO.setBno(bno);
            replyLikeVO.setUid(uid);
            replyLikeVO.setRno(rno);
            likeService.removeReplyLike(replyLikeVO);
            entity = new ResponseEntity<>("REPLY LIKE DELETED", HttpStatus.OK);
        } catch (Exception e) {
            entity = new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
        return entity;
    }

    // 댓글 추천 갯수
    @RequestMapping(value = "/count/{bno}/{rno}", method = RequestMethod.GET)
    public ResponseEntity<Map<String, Object>> countReplyLikes(@PathVariable("bno") Integer bno,
                                                               @PathVariable("rno") Integer rno) {
        ResponseEntity<Map<String, Object>> entity = null;
        try {
            int replyLikeTotalCount = likeService.countReplyLikes(rno);
            Map<String, Object> map = new HashMap<>();
            map.put("replyLikeTotalCount", replyLikeTotalCount);
            entity = new ResponseEntity<>(map, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            entity = new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return entity;
    }

    // 댓글 추천 여부
    @RequestMapping(value = "/check/{bno}/{uid}/{rno}", method = RequestMethod.GET)
    public ResponseEntity<Map<String, Object>> checkReplyLike(@PathVariable("bno") Integer bno,
                                                              @PathVariable("rno") Integer rno,
                                                              @PathVariable("uid") String uid) {
        ResponseEntity<Map<String, Object>> entity = null;
        try {
            boolean checkReplyLike = likeService.checkReplyLike(rno, uid);
            Map<String, Object> map = new HashMap<>();
            map.put("checkReplyLike", checkReplyLike);
            entity = new ResponseEntity<>(map, HttpStatus.OK);
        } catch (Exception e) {
            entity = new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return entity;
    }

}
