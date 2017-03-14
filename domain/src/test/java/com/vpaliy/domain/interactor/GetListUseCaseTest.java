package com.vpaliy.domain.interactor;

import com.vpaliy.domain.model.BookModel;
import com.vpaliy.domain.model.UserModel;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;

@RunWith(MockitoJUnitRunner.class)
public class GetListUseCaseTest extends UseCaseTest {

    @Test
    public void testGetUserListMethod() {
        GetListUseCase<UserModel> getListUseCase=new GetListUseCase<>(mockUserRepository);
        getListUseCase.execute();

        verify(mockUserRepository).getList();
        verifyNoMoreInteractions(mockUserRepository);
    }

    @Test
    public void testGetBookListMethod() {
        GetListUseCase<BookModel> getListUseCase=new GetListUseCase<>(mockBookRepository);
        getListUseCase.execute();

        verify(mockBookRepository).getList();
        verifyNoMoreInteractions(mockBookRepository);
    }


}
