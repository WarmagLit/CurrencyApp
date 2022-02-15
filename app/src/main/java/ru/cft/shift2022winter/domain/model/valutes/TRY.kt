import com.google.gson.annotations.SerializedName
import ru.cft.shift2022winter.domain.model.Valute

data class TRY (
	@SerializedName("ID")
	override val id : String,
	@SerializedName("NumCode")
	override val numCode : Int,
	@SerializedName("CharCode")
	override val charCode : String,
	@SerializedName("Nominal")
	override val nominal : Int,
	@SerializedName("Name")
	override val name : String,
	@SerializedName("Value")
	override val value : Double,
	@SerializedName("Previous")
	override val previous : Double

): Valute(id, numCode, charCode, nominal, name, value, previous)