package emiyaj.clothes

import com.beust.klaxon.Klaxon
import emiyaj.common.CommonRequest
import emiyaj.util.AESEncryption
import emiyaj.util.Validation
import org.springframework.web.bind.annotation.*


@RestController
@RequestMapping(value = ["clothes"])
class ClothesController {
    @RequestMapping(value = ["search/villager"], method = [RequestMethod.POST])
    fun search(@RequestBody request: SearchByVillagerRequest): ClothesResult {
        val data = request.decryptedData
        val index = request.index
        val array = data.split('/')
        if (array.size != 5) return ClothesResult.badRequest()
        val style1: Style? = Style.getByValue(array[1])
        val style2: Style? = Style.getByValue(array[2])
        val color1: Color? = Color.getByValue(array[3])
        val color2: Color? = Color.getByValue(array[4])
        val requestTime: Long? = array[0].toLongOrNull()
        if (requestTime == null || requestTime + 5 < System.currentTimeMillis() / 1000 || color1 == null || color2 == null || style1 == null || style2 == null) {
            return ClothesResult.badRequest()
        }
        try {
            val filtered = ClothesDatabase.getClothesList(style1, style2, color1, color2, request.target)
            return ClothesResult(status = ClothesStatus.SUCCESS, comment = "success", data = AESEncryption.encrypt(Klaxon().toJsonString(filtered)))

        } catch (e: Exception) {
            e.printStackTrace()
        }
        return ClothesResult.badRequest()
    }

    @RequestMapping(value = ["search/name"], method = [RequestMethod.POST])
    fun clothes(@RequestBody request: SearchByNameRequest): ClothesResult {
        val data = request.decryptedData
        val array = data.split('/')
        if (array.size != 3) return ClothesResult.badRequest()
        val name = array[1]
        val color = Color.getByValue(array[2])
        val requestTime: Long? = array[0].toLongOrNull()
        if (requestTime == null || requestTime + 5 < System.currentTimeMillis() / 1000 || color == null || name == "" || name == null) {
            return ClothesResult.badRequest()
        }
        try {
            val clothesPair = ClothesDatabase.getColorAndStyleByName(name, color)
            return ClothesResult(status = ClothesStatus.SUCCESS, comment = "success", data = AESEncryption.encrypt(Klaxon().toJsonString(clothesPair)))

        } catch (e: Exception) {
            e.printStackTrace()
        }
        return ClothesResult.badRequest()
    }

    @PostMapping(value = ["search/id"])
    fun searchById(@RequestBody request: CommonRequest): ClothesResult {
        val data: String = Validation.encryptedTime(request.decryptedData) ?: return ClothesResult.badRequest()
        try {
            val clothes = ClothesDatabase.getClothesByInternalId(data.toInt()) ?: ClothesResult.badRequest()
            return ClothesResult(ClothesStatus.SUCCESS, "success", AESEncryption.encrypt(Klaxon().toJsonString(clothes)))
        } catch (e: Exception) {
            return ClothesResult.badRequest()
        }
    }
}
