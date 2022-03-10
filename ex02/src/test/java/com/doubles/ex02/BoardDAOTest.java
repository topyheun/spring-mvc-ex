package com.doubles.ex02;

import com.doubles.ex02.domain.BoardVO;
import com.doubles.ex02.domain.Criteria;
import com.doubles.ex02.domain.SearchCriteria;
import com.doubles.ex02.persistence.BoardDAO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import javax.inject.Inject;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/spring/applicationContext.xml"})
public class BoardDAOTest {

    @Inject
    private BoardDAO boardDAO;

    private static Logger logger = LoggerFactory.getLogger(BoardDAOTest.class);

    @Test
    public void testCreate() throws Exception {

        for (int i = 1; i <= 1000; i++) {
            BoardVO boardVO = new BoardVO();
            boardVO.setTitle(i+ "번째 글 제목입니다...");
            boardVO.setContent(i+ "번재 글 내용입니다...");
            boardVO.setWriter("user0"+(i%10));

            boardDAO.create(boardVO);
        }

    }

    @Test
    public void testRead() throws Exception {

        for (int i = 1; i <10; i ++) {

            logger.info( i+ "번째 글 조회 : "+ boardDAO.read(i));

        }

    }

    @Test
    public void testUpdate() throws Exception {

        BoardVO boardVO = new BoardVO();
        boardVO.setBno(1000);
        boardVO.setTitle("수정된 글 제목입니다...");
        boardVO.setContent("수정된 글 내용입니다...");

        boardDAO.update(boardVO);

    }

    @Test
    public void testDelete() throws Exception {

        boardDAO.delete(999);

    }

    @Test
    public void TestList() throws Exception {

        logger.info(boardDAO.list().toString());

    }

    @Test
    public void TestCriteria() throws Exception {

        Criteria criteria = new Criteria();
        criteria.setPage(2);
        criteria.setPerPageNum(20);

        List<BoardVO> list = boardDAO.list(criteria);

        for (BoardVO boardVO : list) {
            logger.info(boardVO.getBno() + " : " + boardVO.getTitle());
        }

    }

    @Test
    public void testURI() throws Exception {

        UriComponents uriComponents = UriComponentsBuilder.newInstance()
                .path("/board/read")
                .queryParam("bno", 12)
                .queryParam("perPageNum", 20)
                .build();

        logger.info("/board/read?bno=12&perPageNum=20");
        logger.info(uriComponents.toString());

    }

    @Test
    public void testURI2() throws Exception {

        UriComponents uriComponents = UriComponentsBuilder.newInstance()
                .path("/{module}/{page}")
                .queryParam("bno", 12)
                .queryParam("perPageNum", 20)
                .build()
                .expand("board", "read")
                .encode();

        logger.info("/board/read?bno=12&perPageNum=20");
        logger.info(uriComponents.toString());

    }

    @Test
    public void testDynamic1() throws Exception {

        SearchCriteria criteria = new SearchCriteria();
        criteria.setPage(1);
        criteria.setKeyword("글");
        criteria.setSearchType("t");

        logger.info("===================================");

        List<BoardVO> list = boardDAO.list(criteria);

        for (BoardVO boardVO : list) {
            logger.info(boardVO.getBno() + " : " + boardVO.getTitle());
        }

        logger.info("===================================");

        logger.info("COUNT : " + boardDAO.listCount(criteria));
    }
}
