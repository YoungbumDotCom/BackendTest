package emiyaj.news

import com.beust.klaxon.Klaxon
import emiyaj.common.CommonRequest
import emiyaj.common.CommonResult
import emiyaj.common.CommonStatus
import emiyaj.news.model.News
import emiyaj.news.model.NewsResponse
import emiyaj.util.SanitizeHTML.sanitizeHTML
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping(value = ["news"])
class NewsController {
    @GetMapping(value = ["/get"])
    fun getDefault() = NewsResponse(NewsDatabase.getNews(5, 0))

    @GetMapping(value = ["/get/{page}"])
    fun get(@PathVariable page: Int = 1) = NewsResponse(NewsDatabase.getNews(10, (page - 1) * 10))

    /*@PostMapping(value = ["/post"])
    fun post(@RequestBody body: CommonRequest): CommonResult {
        val req = Klaxon().parse<News>(body.decryptedData) ?: return CommonResult(CommonStatus.NOT_OPERATIONAL, "bad request")
        NewsDatabase.postNews(req)
        return CommonResult(CommonStatus.NORMAL, "success")
    }
    */
}