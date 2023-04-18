package com.meridiane.teacher.data

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.meridiane.teacher.domain.models.Question

class PageSourceQuestion(
    private val repository: DemoRepository,
) : PagingSource<Int, Question>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Question> {

        return try {
            val pageNumber = params.key ?: 0
            val students = repository.getQuestions()
            val prevKey = if (pageNumber > 0) pageNumber - 1 else null
            val nextKey = if (students.isNotEmpty()) pageNumber + 1 else null

            LoadResult.Page(data = students, prevKey = prevKey, nextKey = nextKey)
        } catch (e: Throwable) {
            LoadResult.Error(
                throwable = e
            )
        }
    }

    override fun getRefreshKey(state: PagingState<Int, Question>): Int? {
        return state.anchorPosition?.let {
            state.closestPageToPosition(it)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(it)?.nextKey?.minus(1)
        }
    }

}
