package com.example.searchbasic.respository;

import com.example.searchbasic.etc.SearchKeyword;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SearchKeywordRepository extends JpaRepository<SearchKeyword, String> {
}
