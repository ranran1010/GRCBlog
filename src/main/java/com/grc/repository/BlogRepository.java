package com.grc.repository;

import com.grc.entity.Blog;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by 14437 on 2017/6/21.
 */
public interface BlogRepository extends JpaRepository<Blog,Integer> {
}
