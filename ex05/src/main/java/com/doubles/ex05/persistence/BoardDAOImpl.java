package com.doubles.ex05.persistence;

import com.doubles.ex05.domain.BoardLikeVO;
import com.doubles.ex05.domain.BoardVO;
import com.doubles.ex05.domain.Criteria;
import com.doubles.ex05.domain.SearchCriteria;
import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import javax.inject.Inject;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class BoardDAOImpl implements BoardDAO {

    @Inject
    private SqlSession sqlSession;

    private static final String NAMESPACE = "com.doubles.ex05.mapper.BoardMapper";


    // 게시글 입력
    @Override
    public void create(BoardVO boardVO) throws Exception {
        sqlSession.insert(NAMESPACE + ".create", boardVO);
    }

    // 게시글 조회
    @Override
    public BoardVO read(Integer bno) throws Exception {
        return sqlSession.selectOne(NAMESPACE + ".read", bno);
    }

    // 게시글 조회수 증가
    @Override
    public void updateViewCnt(Integer bno) throws Exception {
        sqlSession.update(NAMESPACE + ".updateViewCnt", bno);
    }

    // 게시글 수정
    @Override
    public void update(BoardVO boardVO) throws Exception {
        sqlSession.update(NAMESPACE + ".update", boardVO);
    }

    // 게시글 삭제
    @Override
    public void delete(Integer bno) throws Exception {
        sqlSession.delete(NAMESPACE + ".delete", bno);
    }

    // 게시글 목록
    @Override
    public List<BoardVO> list() throws Exception {
        return sqlSession.selectList(NAMESPACE + ".list");
    }

    // 게시글 목록 + 페이징
    @Override
    public List<BoardVO> list(Criteria criteria) throws Exception {
        return sqlSession.selectList(NAMESPACE + ".pagingList", criteria);
    }

    // 게시글 전체 갯수
    @Override
    public int countList(Criteria criteria) throws Exception {
        return sqlSession.selectOne(NAMESPACE + ".countList", criteria);
    }

    // 게시글 목록 + 페이징 + 검색
    @Override
    public List<BoardVO> list(SearchCriteria criteria) throws Exception {
        return sqlSession.selectList(NAMESPACE + ".pagingSearchedList", criteria);
    }

    // 게시글 전체 갯수 or 검색된 게시글 갯수
    @Override
    public int countSearchedList(SearchCriteria criteria) throws Exception {
        return sqlSession.selectOne(NAMESPACE + ".countSearchedList", criteria);
    }

    // 게시글 댓글 갯수 갱신
    @Override
    public void updateReplyCnt(Integer bno, int amount) throws Exception {
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("bno", bno);
        paramMap.put("amount", amount);
        sqlSession.update(NAMESPACE + ".updateReplyCnt", paramMap);
    }

    // 회원이 작성한 게시글 목록
    @Override
    public List<BoardVO> userBoardList(String uid) throws Exception {
        return sqlSession.selectList(NAMESPACE + ".userBoardList", uid);
    }
}
