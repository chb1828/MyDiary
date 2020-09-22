package com.chb.scheduler.domain.repository;

import com.chb.scheduler.domain.entity.Diary;
import org.springframework.data.jpa.repository.JpaRepository;


public interface DiaryRepository extends JpaRepository<Diary,Long> {


}
