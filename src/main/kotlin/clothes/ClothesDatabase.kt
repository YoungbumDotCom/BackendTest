package emiyaj.clothes

import emiyaj.clothes.model.Clothes
import emiyaj.clothes.model.ClothesJson
import emiyaj.util.EnvVariable
import java.sql.Connection
import java.sql.DriverManager
import java.sql.PreparedStatement
import java.sql.ResultSet

object ClothesDatabase {
    private fun getConnection(): Connection {
        val db = javaClass.getResource("/clothes.db")
        //return DriverManager.getConnection("jdbc:sqlite::memory:")
        return DriverManager.getConnection("jdbc:sqlite::resource:clothes.db")
    }

    fun getClothesList(style1: Style, style2: Style, color1: Color, color2: Color, target: Int): List<Clothes> {
        val conn = getConnection()
        val ps: PreparedStatement
        when (target) {
            0 -> { // Match Styles and Colors
                ps = conn.prepareStatement("SELECT * FROM clothes WHERE (style1 = ? OR style1 = ?) AND (color1 = ? OR color2 = ? OR color1 =? OR color2 = ?) AND type != 'bottom'")
                ps.setString(1, style1.value)
                ps.setString(2, style2.value)
                ps.setString(3, color1.value)
                ps.setString(4, color2.value)
                ps.setString(5, color2.value)
                ps.setString(6, color1.value)
            }
            1 -> { // Match Styles
                ps = conn.prepareStatement("SELECT * FROM clothes WHERE (style1 = ? OR style1 = ?) AND type != 'bottom'")
                ps.setString(1, style1.value)
                ps.setString(2, style2.value)
            }
            2 -> { // Match Colors
                ps = conn.prepareStatement("SELECT * FROM clothes WHERE (color1 = ? OR color2 = ? OR color1 =? OR color2 = ?) AND type != 'bottom'")
                ps.setString(1, color1.value)
                ps.setString(2, color2.value)
                ps.setString(3, color2.value)
                ps.setString(4, color1.value)
            }
            3 -> { // Match Exact two colors
                ps = conn.prepareStatement("SELECT * FROM clothes WHERE ((color1 = ? AND color2 = ?) OR (color1 =? AND color2 = ?)) OR ((color1 = ? and color2 = ?) or (color1 = ? and color2=?)) AND type != 'bottom'")
                ps.setString(1, color1.value)
                ps.setString(2, color2.value)
                ps.setString(3, color2.value)
                ps.setString(4, color1.value)
                ps.setString(5, color1.value)
                ps.setString(6, color1.value)
                ps.setString(7, color2.value)
                ps.setString(8, color2.value)
            }
            4 -> { // Match Exact two colors and Exact style
                ps = conn.prepareStatement("SELECT * FROM clothes WHERE (style = ? OR style = ?) AND (((color1 = ? AND color2 = ?) OR (color1 =? AND color2 = ?)) OR ((color1 = ? and color2 = ?) or (color1 = ? and color2=?))) AND type != 'bottom'")
                ps.setString(1, style1.value)
                ps.setString(2, style2.value)
                ps.setString(3, color1.value)
                ps.setString(4, color2.value)
                ps.setString(5, color2.value)
                ps.setString(6, color1.value)
                ps.setString(7, color1.value)
                ps.setString(8, color1.value)
                ps.setString(9, color2.value)
                ps.setString(10, color2.value)
            }
            else -> {
                return listOf()
            }
        }
        val rs = ps.executeQuery()
        val clothes = mutableListOf<Clothes>()
        while (rs.next()) {
            clothes.add(resultSetToClothes(rs))
        }
        conn.close()
        return clothes
    }

    fun getColorAndStyleByName(name: String, color: Color): Pair<Int, Clothes?> {
        val conn = getConnection()
        val ps = conn.prepareStatement("SELECT *, count(*) as count FROM clothes WHERE (color1 = ? or color2 = ?) AND (name_kor like ? OR name like ?)")
        ps.setString(1, color.value)
        ps.setString(2, color.value)
        ps.setString(3, "%$name%")
        ps.setString(4, "%$name%")
        val rs = ps.executeQuery()
        return if (rs.next()) {
            if (rs.getInt("count") > 0) {
                Pair(rs.getInt("count"), resultSetToClothes(rs))
            } else {
                Pair(0, null)
            }
        } else {
            Pair(0, null)
        }
    }

    fun getClothesByInternalId(id: Int): Clothes? {
        val conn = getConnection()
        val rs = conn.prepareStatement("SELECT * FROM clothes WHERE internalid = $id").executeQuery()
        return if (rs.next()) {
            resultSetToClothes(rs)
        } else {
            null
        }
    }

    fun getClothesByFilename(filename: String): Clothes? {
        val conn = getConnection()
        val rs = conn.prepareStatement("SELECT * FROM clothes WHERE filename = '$filename'").executeQuery()
        return if (rs.next()) {
            resultSetToClothes(rs)
        } else {
            null
        }
    }

    private fun resultSetToClothes(rs: ResultSet): Clothes {
        val style1 = Style.getByValue(rs.getString("style1"))
        val style2 = Style.getByValue(rs.getString("style2"))
        val season = Season.getByValue(rs.getString("season"))
        //val catalog = Catalog.getByValue(rs.getString("catalog"))
        val source = Source.getByValue(rs.getString("source"))
        val type = Type.getByValue(rs.getString("type"))

        //if (type != null && style1 != null && style2 != null && season != null /*&& catalog != null*/ && source != null) {
        return Clothes(
            uniqueId = rs.getString("uniqueid"),
            name = rs.getString("name"),
            nameKorean = rs.getString("name_kor"),
            buy = rs.getInt("buy"),
            sell = rs.getInt("sell"),
            miles = rs.getInt("miles"),
            color1 = Color.valueOf(rs.getString("color1").capitalize()),
            color2 = Color.valueOf(rs.getString("color2").capitalize()),
            style1 = style1 ?: Style.NotImplemented,
            style2 = style2 ?: Style.NotImplemented,
            season = season ?: Season.ALL,
            mannequin = rs.getBoolean("mannequin"),
            catalog = null,
            source = Source.DODO_AIRLINES,
            internalId = rs.getInt("internalid"),
            filename = rs.getString("filename"),
            variation = "variation",
            type = Type.UMBRELLA
        )
        /*} else {
            println("Database corrupted. Exiting...")
            exitProcess(2)
        }*/
    }

    fun removeData() {
        val conn = getConnection()
        conn.prepareStatement("DELETE FROM clothes").execute()
    }

    fun updateData(clothes: List<ClothesJson>) {
        val conn = getConnection()
        clothes.forEach { c ->
            if (c.variations?.isEmpty() == false) {
                c.variations.forEach { v ->
                    val ps = conn.prepareStatement(
                        "INSERT INTO clothes " +
                                "(uniqueid, name, name_kor, buy, sell, miles, color1, color2, style1, season, mannequin, themes, catalog, source, internalid, filename, cdn, variation, diy, type, style2) VALUES " +
                                "(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)")
                    ps.setString(1, v.uniqueEntryId)
                    ps.setString(2, c.name)
                    ps.setString(3, c.translations.kRko)
                    ps.setInt(4, c.buy ?: 0)
                    ps.setInt(5, c.sell ?: -1)
                    ps.setInt(6, -1)
                    ps.setString(7, v.colors.firstOrNull())
                    ps.setString(8, v.colors.elementAtOrNull(1))
                    ps.setString(9, c.styles.first().value)
                    ps.setString(10, c.seasonality)
                    ps.setString(11, c.mannequinSeason)
                    ps.setString(12, c.theme.toString())
                    ps.setNull(13, 0)
                    ps.setString(14, c.source?.firstOrNull())
                    ps.setLong(15, v.internalId)
                    ps.setString(16, v.filename)
                    ps.setNull(17, 0)
                    ps.setString(18, v.variation.toString())
                    ps.setInt(19, 0)//if (c.diy) 1 else 0)
                    ps.setString(20, c.sourceSheet?.toLowerCase()?.dropLast(1))
                    ps.setString(21, c.styles[1].value)
                    ps.executeUpdate()
                    ps.close()
                }
            } else {
                val ps = conn.prepareStatement(
                    "INSERT INTO clothes " +
                            "(uniqueid, name, name_kor, buy, sell, miles, color1, color2, style1, season, mannequin, themes, catalog, source, internalid, filename, cdn, variation, diy, type, style2) VALUES " +
                            "(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)")
                ps.setString(1, c.uniqueEntryID)
                ps.setString(2, c.name)
                ps.setString(3, c.translations.kRko)
                ps.setInt(4, c.buy ?: 0)
                ps.setInt(5, c.sell ?: -1)
                ps.setInt(6, -1)
                ps.setString(7, c.colors.firstOrNull()?.toString())
                ps.setString(8, c.colors.elementAtOrNull(1)?.toString())
                ps.setString(9, c.styles.first().value)
                ps.setString(10, c.seasonality)
                ps.setString(11, c.mannequinSeason)
                ps.setString(12, c.theme.toString())
                ps.setNull(13, 0)
                ps.setString(14, c.source?.firstOrNull())
                c.internalID?.let { ps.setInt(15, it) }
                ps.setString(16, c.filename)
                ps.setNull(17, 0)
                ps.setString(18, c.variation.toString())
                ps.setInt(19, 0)//if (c.diy) 1 else 0)
                ps.setString(20, c.sourceSheet?.toLowerCase()?.dropLast(1))
                ps.setString(21, c.styles.last().value)
                ps.executeUpdate()
                ps.close()
            }
        }
    }
}