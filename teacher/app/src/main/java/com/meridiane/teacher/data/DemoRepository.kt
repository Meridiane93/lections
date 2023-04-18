package com.meridiane.teacher.data

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.meridiane.teacher.domain.interface_repository.*
import com.meridiane.teacher.domain.models.Lesson
import com.meridiane.teacher.domain.models.Question
import com.meridiane.teacher.domain.models.Student
import com.meridiane.teacher.domain.models.Teacher
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import java.util.*

class DemoRepository :
    InterfaceRegistrationRepository,
    InterfaceAuthorizationRepository,
    InterfaceGetProfileRepository,
    InterfaceGetStudentsRepository,
    InterfaceGetQuestionsRepository,
    InterfaceGetLessonRepository {

    override suspend fun getProfile(): Result<Teacher> {
        delay(3000L)
        return randomResultProfile(
            Teacher(
                "Петрова Анна Сергеевна",
                "352231@mail.ru",
                "",
                "1234567",
                "12",
                1,
                "https://www.gravatar.com/avatar/205e460b479e2e5b48aec07710c08d50?s=200"
            )
        )
    }

    override suspend fun getStudents(): List<Student> {
        randomDelay()
        return listOf(
            Student(
                "Комяков Олег Борисович",
                "@User1",
                12,
                "Самара, улица Весенняя д 34 кв 3",
                "7-987-654-32-10",
                "https://db4sgowjqfwig.cloudfront.net/images/1663879/Burgen_Cadwallader.jpg"
            ),
            Student(
                "Самарин Сергей Валентинович",
                "@User2",
                21,
                "Ульяновск, улица Городская д 67 кв 17",
                "7-910-234-56-78",
                "https://sun9-19.userapi.com/c852220/v852220504/118e7/5BWvWHcfJ10.jpg"
            ),
            Student(
                "Ларина Анна Николаевна",
                "@User3",
                3,
                "Нижний Новгород, улица Заречная д 7 кв 7",
                "7-987-654-32-10",
                "https://i.ytimg.com/vi/UPV86oNZjOY/hqdefault.jpg"
            ),
            Student(
                "Комяков Олег Борисович",
                "@User1",
                12,
                "Самара, улица Весенняя д 34 кв 3",
                "7-987-654-32-10",
                "https://db4sgowjqfwig.cloudfront.net/images/1663879/Burgen_Cadwallader.jpg"
            ),
            Student(
                "Самарин Сергей Валентинович",
                "@User2",
                21,
                "Ульяновск, улица Городская д 67 кв 17",
                "7-910-234-56-78",
                "https://sun9-19.userapi.com/c852220/v852220504/118e7/5BWvWHcfJ10.jpg"
            ),
            Student(
                "Ларина Анна Николаевна",
                "@User3",
                3,
                "Нижний Новгород, улица Заречная д 7 кв 7",
                "7-987-654-32-10",
                "https://i.ytimg.com/vi/UPV86oNZjOY/hqdefault.jpg"
            ),
            Student(
                "Комяков Олег Борисович",
                "@User1",
                12,
                "Самара, улица Весенняя д 34 кв 3",
                "7-987-654-32-10",
                "https://db4sgowjqfwig.cloudfront.net/images/1663879/Burgen_Cadwallader.jpg"
            ),
            Student(
                "Самарин Сергей Валентинович",
                "@User2",
                21,
                "Ульяновск, улица Городская д 67 кв 17",
                "7-910-234-56-78",
                "https://sun9-19.userapi.com/c852220/v852220504/118e7/5BWvWHcfJ10.jpg"
            ),
            Student(
                "Ларина Анна Николаевна",
                "@User3",
                3,
                "Нижний Новгород, улица Заречная д 7 кв 7",
                "7-987-654-32-10",
                "https://i.ytimg.com/vi/UPV86oNZjOY/hqdefault.jpg"
            ),
            Student(
                "Комяков Олег Борисович",
                "@User1",
                12,
                "Самара, улица Весенняя д 34 кв 3",
                "7-987-654-32-10",
                "https://db4sgowjqfwig.cloudfront.net/images/1663879/Burgen_Cadwallader.jpg"
            ),
            Student(
                "Самарин Сергей Валентинович",
                "@User2",
                21,
                "Ульяновск, улица Городская д 67 кв 17",
                "7-910-234-56-78",
                "https://sun9-19.userapi.com/c852220/v852220504/118e7/5BWvWHcfJ10.jpg"
            ),
            Student(
                "Ларина Анна Николаевна",
                "@User3",
                3,
                "Нижний Новгород, улица Заречная д 7 кв 7",
                "7-987-654-32-10",
                "https://i.ytimg.com/vi/UPV86oNZjOY/hqdefault.jpg"
            ),
            Student(
                "Комяков Олег Борисович",
                "@User1",
                12,
                "Самара, улица Весенняя д 34 кв 3",
                "7-987-654-32-10",
                "https://db4sgowjqfwig.cloudfront.net/images/1663879/Burgen_Cadwallader.jpg"
            ),
            Student(
                "Самарин Сергей Валентинович",
                "@User2",
                21,
                "Ульяновск, улица Городская д 67 кв 17",
                "7-910-234-56-78",
                "https://sun9-19.userapi.com/c852220/v852220504/118e7/5BWvWHcfJ10.jpg"
            ),
            Student(
                "Ларина Анна Николаевна",
                "@User3",
                3,
                "Нижний Новгород, улица Заречная д 7 кв 7",
                "7-987-654-32-10",
                "https://i.ytimg.com/vi/UPV86oNZjOY/hqdefault.jpg"
            )
        )
    }

    override suspend fun getQuestions(): List<Question> {
        randomDelay()
        return listOf(
            Question(
                "Устраивают ли сервисы покупок",
                "https://docs.google.com/forms/d/e/1FAIpQLSdL_atU5lQ8_qgYNnQaAHkYU6Q5QXS96fC_hheU24XwBAedGw/viewform?usp=sf_link",
                1
            ),
            Question(
                "Как прошли занятия",
                "https://docs.google.com/forms/d/e/1FAIpQLSdL_atU5lQ8_qgYNnQaAHkYU6Q5QXS96fC_hheU24XwBAedGw/viewform?usp=sf_link",
                2
            ),
            Question(
                "Достаточно ли знаний получили",
                "https://docs.google.com/forms/d/e/1FAIpQLSdL_atU5lQ8_qgYNnQaAHkYU6Q5QXS96fC_hheU24XwBAedGw/viewform?usp=sf_link",
                3
            ),
            Question(
                "Какой учителя вы бы хотели для себя",
                "https://docs.google.com/forms/d/e/1FAIpQLSdL_atU5lQ8_qgYNnQaAHkYU6Q5QXS96fC_hheU24XwBAedGw/viewform?usp=sf_link",
                4
            ),
            Question(
                "Устраивают ли сервисы покупок",
                "https://docs.google.com/forms/d/e/1FAIpQLSdL_atU5lQ8_qgYNnQaAHkYU6Q5QXS96fC_hheU24XwBAedGw/viewform?usp=sf_link",
                5
            ),
            Question(
                "Как прошли занятия",
                "https://docs.google.com/forms/d/e/1FAIpQLSdL_atU5lQ8_qgYNnQaAHkYU6Q5QXS96fC_hheU24XwBAedGw/viewform?usp=sf_link",
                6
            ),
            Question(
                "Достаточно ли знаний получили",
                "https://docs.google.com/forms/d/e/1FAIpQLSdL_atU5lQ8_qgYNnQaAHkYU6Q5QXS96fC_hheU24XwBAedGw/viewform?usp=sf_link",
                7
            ),
            Question(
                "Какой учителя вы бы хотели для себя",
                "https://docs.google.com/forms/d/e/1FAIpQLSdL_atU5lQ8_qgYNnQaAHkYU6Q5QXS96fC_hheU24XwBAedGw/viewform?usp=sf_link",
                8
            ),
            Question(
                "Устраивают ли сервисы покупок",
                "https://docs.google.com/forms/d/e/1FAIpQLSdL_atU5lQ8_qgYNnQaAHkYU6Q5QXS96fC_hheU24XwBAedGw/viewform?usp=sf_link",
                9
            ),
            Question(
                "Как прошли занятия",
                "https://docs.google.com/forms/d/e/1FAIpQLSdL_atU5lQ8_qgYNnQaAHkYU6Q5QXS96fC_hheU24XwBAedGw/viewform?usp=sf_link",
                10
            ),
            Question(
                "Достаточно ли знаний получили",
                "https://docs.google.com/forms/d/e/1FAIpQLSdL_atU5lQ8_qgYNnQaAHkYU6Q5QXS96fC_hheU24XwBAedGw/viewform?usp=sf_link",
                11
            ),
            Question(
                "Какой учителя вы бы хотели для себя",
                "https://docs.google.com/forms/d/e/1FAIpQLSdL_atU5lQ8_qgYNnQaAHkYU6Q5QXS96fC_hheU24XwBAedGw/viewform?usp=sf_link",
                12
            )
        )
    }

    override suspend fun getLesson(boolean: Boolean?): List<Lesson> {
        randomDelay()

        val allLesson = listOf(
            Lesson(
                "18/04/23",
                "Методы разработки архитектуры",
                null,
                "Методы проектирования архитектуры программного обеспечения",
                "1",
                "12",
                1681766724,
                null,
                "1"
            ),
            Lesson(
                "18/04/23",
                "Управление конфигурацией",
                null,
                "Основы управления конфигурацией программных средств\n" +
                        "Стандарты управления конфигурацией программных средств\n" +
                        "Инструменты автоматизации управления конфигурацией программных\n" +
                        "средств\n",
                "2",
                "13",
                1681766873,
                null,
                "2"
            ),
            Lesson(
                "18/04/23",
                "Качество программного обеспечения. Верификация, аттестация, тестирование",
                null,
                "Качество программного обеспечения, его характеристики и\n" +
                        "управление качеством\n" +
                        "Верификация, аттестация и тестирование программного обеспечения\n" +
                        "Методы верификации программного обеспечения\n",
                "3",
                "15",
                1681766992,
                null,
                "3"
            ),
            Lesson(
                "18/04/23",
                "Методы разработки архитектуры",
                null,
                "Методы проектирования архитектуры программного обеспечения",
                "1",
                "12",
                1681766724,
                null,
                "1"
            ),
            Lesson(
                "18/04/23",
                "Управление конфигурацией",
                null,
                "Основы управления конфигурацией программных средств\n" +
                        "Стандарты управления конфигурацией программных средств\n" +
                        "Инструменты автоматизации управления конфигурацией программных\n" +
                        "средств\n",
                "2",
                "13",
                1681766873,
                null,
                "2"
            ),
            Lesson(
                "18/04/23",
                "Качество программного обеспечения. Верификация, аттестация, тестирование",
                null,
                "Качество программного обеспечения, его характеристики и\n" +
                        "управление качеством\n" +
                        "Верификация, аттестация и тестирование программного обеспечения\n" +
                        "Методы верификации программного обеспечения\n",
                "3",
                "15",
                1681766992,
                null,
                "3"
            ),
            Lesson(
                "18/04/23",
                "Методы разработки архитектуры",
                null,
                "Методы проектирования архитектуры программного обеспечения",
                "1",
                "12",
                1681766724,
                null,
                "1"
            ),
            Lesson(
                "18/04/23",
                "Управление конфигурацией",
                null,
                "Основы управления конфигурацией программных средств\n" +
                        "Стандарты управления конфигурацией программных средств\n" +
                        "Инструменты автоматизации управления конфигурацией программных\n" +
                        "средств\n",
                "2",
                "13",
                1681766873,
                null,
                "2"
            ),
            Lesson(
                "18/04/23",
                "Качество программного обеспечения. Верификация, аттестация, тестирование",
                null,
                "Качество программного обеспечения, его характеристики и\n" +
                        "управление качеством\n" +
                        "Верификация, аттестация и тестирование программного обеспечения\n" +
                        "Методы верификации программного обеспечения\n",
                "3",
                "15",
                1681766992,
                null,
                "3"
            ),
            Lesson(
                "18/04/23",
                "Качество программного обеспечения. Верификация, аттестация, тестирование",
                null,
                "Качество программного обеспечения, его характеристики и\n" +
                        "управление качеством\n" +
                        "Верификация, аттестация и тестирование программного обеспечения\n" +
                        "Методы верификации программного обеспечения\n",
                "3",
                "15",
                2681832446455,
                null,
                "3"
            ),
            Lesson(
                "18/04/23",
                "Качество программного обеспечения. Верификация, аттестация, тестирование",
                null,
                "Качество программного обеспечения, его характеристики и\n" +
                        "управление качеством\n" +
                        "Верификация, аттестация и тестирование программного обеспечения\n" +
                        "Методы верификации программного обеспечения\n",
                "3",
                "15",
                2381832446455,
                null,
                "3"
            ),
            Lesson(
                "18/04/23",
                "Качество программного обеспечения. Верификация, аттестация, тестирование",
                null,
                "Качество программного обеспечения, его характеристики и\n" +
                        "управление качеством\n" +
                        "Верификация, аттестация и тестирование программного обеспечения\n" +
                        "Методы верификации программного обеспечения\n",
                "3",
                "15",
                2181832446455,
                null,
                "3"
            )
        )

        val (oldLessons, futureLessons) = allLesson.partition { it.date!! < Calendar.getInstance().timeInMillis }

        return when (boolean) {
            null -> allLesson
            true -> futureLessons
            else -> oldLessons
        }
    }

    override suspend fun getSizeLesson(): List<Int> {
        val future = getLesson(true).size
        val old = getLesson(false).size
        return listOf(future, old)
    }

    override fun getListStudents(): Flow<PagingData<Student>> {
        return Pager(
            config = PagingConfig(
                pageSize = 2,
                enablePlaceholders = false,
                initialLoadSize = 2,
                prefetchDistance = 2 / 2
            ),
            pagingSourceFactory = {
                PageSource(this)
            }
        ).flow
    }

    override fun getListQuestions(): Flow<PagingData<Question>> {
        return Pager(
            config = PagingConfig(
                pageSize = 2,
                enablePlaceholders = false,
                initialLoadSize = 2,
                prefetchDistance = 2 / 2
            ),
            pagingSourceFactory = {
                PageSourceQuestion(this)
            }
        ).flow
    }

    override fun getListLesson(boolean: Boolean?): Flow<PagingData<Lesson>> {
        return Pager(
            config = PagingConfig(
                pageSize = 2,
                enablePlaceholders = false,
                initialLoadSize = 2,
                prefetchDistance = 2 / 2
            ),
            pagingSourceFactory = {
                PageSourceLesson(this, boolean)
            }
        ).flow
    }

    override suspend fun registration(): Result<String> {
        delay(2000L)
        return randomResultProfile("is success")
    }

    override suspend fun authorization(): Result<String> {
        delay(3000L)
        return randomResultProfile("is success")
    }

    private fun <T> randomResultProfile(data: T): Result<T> =
        if ((0..100).random() < 99) {
            Result.success(data)
        } else {
            Result.failure(RuntimeException())
        }

    private suspend fun randomDelay() {
        delay((1000L..2000L).random())
    }
}

