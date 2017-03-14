package com.vpaliy.domain.interactor;

import com.vpaliy.domain.model.BookModel;
import com.vpaliy.domain.model.UserModel;
import com.vpaliy.domain.repository.IRepository;

import org.junit.runner.RunWith;
import org.mockito.Mock;


abstract class UseCaseTest {

    @Mock
    protected UserModel FAKE_USER;

    @Mock
    protected BookModel FAKE_BOOK;

    @Mock
    protected IRepository<UserModel> mockUserRepository;

    @Mock
    protected IRepository<BookModel> mockBookRepository;

}
