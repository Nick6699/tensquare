package com.tensquare.qa.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.tensquare.qa.pojo.Problem;
import org.springframework.data.jpa.repository.Query;

import java.awt.*;


/**
 * 数据访问接口
 * @author Administrator
 *
 */
public interface ProblemDao extends JpaRepository<Problem,String>,JpaSpecificationExecutor<Problem>{
    @Query(value ="SELECT * FROM tb_problem,tb_pl WHERE id=problemid AND labelid=?1 ORDER BY updatetime DESC",nativeQuery = true) //使用sql语句
    public Page<Problem> newList(String labelid, Pageable pageable);
    @Query(value = "SELECT * FROM tb_problem,tb_pl WHERE id=problemid AND labelid=?1 ORDER BY reply DESC",nativeQuery = true)
    public Page<Problem> hotList(String labelid, Pageable pageable);
    @Query(value = "SELECT * FROM tb_problem,tb_pl WHERE id=problemid AND labelid=?1 AND solve='0' ORDER BY createtime DESC",nativeQuery = true)
    public Page<Problem>waitList(String labelid,Pageable pageable);
    @Query(value ="SELECT * FROM tb_problem where labelid=?1" ,nativeQuery = true)
    public Page<Problem>findAllPage(String labelid,Pageable pageable);

	
}
