package com.example.searchbasic.etc;

import lombok.Getter;

@Getter
public class SearchKeywordDto {
    private final String keyword;

    private final Long searchCnt;

    public SearchKeywordDto(SearchKeyword searchKeyword) {
        keyword = searchKeyword.getKeyword();
        searchCnt = searchKeyword.getSearchCnt();
    }
}
