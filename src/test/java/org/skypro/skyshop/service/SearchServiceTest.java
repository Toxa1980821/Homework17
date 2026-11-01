package org.skypro.skyshop.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.skypro.skyshop.model.article.Article;
import org.skypro.skyshop.model.search.SearchResult;
import org.skypro.skyshop.model.search.Searchable;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class SearchServiceTest {

    @Mock
    private StorageService storageService;

    @InjectMocks
    private SearchService searchService;

    private Searchable article(String title) {
        return new Article(UUID.randomUUID(), title, "Текст статьи " + title);
    }

    @Test
    void searchReturnsEmptyWhenStorageIsEmpty() {
        when(storageService.getAllSearchable()).thenReturn(Collections.emptyList());

        assertThat(searchService.search("что угодно")).isEmpty();
    }

    @Test
    void searchReturnsEmptyWhenNothingMatches() {
        when(storageService.getAllSearchable()).thenReturn(List.of(
                article("Ноутбук"),
                article("Мышь")
        ));

        assertThat(searchService.search("телефон")).isEmpty();
    }

    @Test
    void searchReturnsMatchingObject() {
        when(storageService.getAllSearchable()).thenReturn(List.of(
                article("TestProduct"),
                article("Ноутбук")
        ));

        Collection<SearchResult> results = searchService.search("Test");

        assertThat(results).hasSize(1);
        assertThat(results).first().extracting(SearchResult::getName).isEqualTo("TestProduct");
    }

    @Test
    void searchIsCaseInsensitive() {
        when(storageService.getAllSearchable()).thenReturn(List.of(article("TestProduct")));

        assertThat(searchService.search("test")).hasSize(1);
    }
}

