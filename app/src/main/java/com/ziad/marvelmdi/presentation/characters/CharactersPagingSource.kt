package com.ziad.marvelmdi.presentation.characters

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.ziad.marvelmdi.data.remote.ApiInterface
import javax.inject.Inject

class CharactersPagingSource @Inject constructor(private val api: ApiInterface) :
    PagingSource<Int, com.ziad.marvelmdi.data.remote.model.Character>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, com.ziad.marvelmdi.data.remote.model.Character> {
        val page = params.key ?: 0
        return try {
            val response = api.getCharacters(page, 10)
            val characters = response.data.results
            LoadResult.Page(
                data = characters ?: emptyList(),
                prevKey = if (page == 1) null else page - 1,
                nextKey = if (characters?.isEmpty() == true) null else page + 1
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, com.ziad.marvelmdi.data.remote.model.Character>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }

}