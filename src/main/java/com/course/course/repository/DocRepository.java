package com.course.course.repository;



import org.springframework.data.jpa.repository.JpaRepository;

import com.course.course.model.Doc;

public interface DocRepository extends JpaRepository<Doc,Integer>{

}
