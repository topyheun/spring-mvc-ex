package com.doubles.ex01.service;

import com.doubles.ex01.domain.BoardVO;
import com.doubles.ex01.domain.Criteria;
import com.doubles.ex01.domain.SearchCriteria;
import com.doubles.ex01.persistence.BoardDAO;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.util.List;

@Service
public class BoardServiceImpl implements BoardService {

    @Inject
    private BoardDAO boardDAO;


    @Override
    public void register(BoardVO boardVO) throws Exception {

        boardDAO.create(boardVO);

    }

    @Override
    public BoardVO read(Integer bno) throws Exception {

        return boardDAO.read(bno);

    }

    @Override
    public void modify(BoardVO boardVO) throws Exception {

        boardDAO.update(boardVO);

    }

    @Override
    public void remove(Integer bno) throws Exception {

        boardDAO.delete(bno);

    }

    @Override
    public List<BoardVO> listAll() throws Exception {

        return boardDAO.listAll();

    }

    @Override
    public List<BoardVO> listCriteria(Criteria criteria) throws Exception {

        return boardDAO.listCriteria(criteria);

    }

    @Override
    public int listCountCriteria(Criteria criteria) throws Exception {

        return boardDAO.countPaging(criteria);

    }

    @Override
    public List<BoardVO> listSearchCriteria(SearchCriteria criteria) throws Exception {

        return boardDAO.listSearch(criteria);

    }

    @Override
    public int listSearchCount(SearchCriteria criteria) throws Exception {

        return boardDAO.listSearchCount(criteria);

    }
}
