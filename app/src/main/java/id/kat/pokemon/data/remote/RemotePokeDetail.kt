package id.kat.pokemon.data.remote
import com.google.gson.annotations.SerializedName


/**
 *
 * Created by Wanhar Aderta Daeng Maro on 5/27/2018.
 * Email : wanhardaengmaro@gmail.com
 *
 */

data class RemotePokeDetail(
    @SerializedName("forms") var forms: List<Form>,
    @SerializedName("abilities") var abilities: List<Abilities>,
    @SerializedName("stats") var stats: List<Stats>,
    @SerializedName("name") var name: String,
    @SerializedName("weight") var weight: Int,
    @SerializedName("moves") var moves: List<Moves>,
    @SerializedName("sprites") var sprites: Sprites,
    @SerializedName("held_items") var heldItems: List<HeldItem>,
    @SerializedName("location_area_encounters") var locationAreaEncounters: String,
    @SerializedName("height") var height: Int,
    @SerializedName("is_default") var isDefault: Boolean,
    @SerializedName("species") var species: Species,
    @SerializedName("id") var id: Int,
    @SerializedName("order") var order: Int,
    @SerializedName("game_indices") var gameIndices: List<GameIndice>,
    @SerializedName("base_experience") var baseExperience: Int,
    @SerializedName("types") var types: List<Type>
)

data class Abilities(
    @SerializedName("slot") var slot: Int,
    @SerializedName("is_hidden") var isHidden: Boolean,
    @SerializedName("ability") var ability: Ability
)

data class Ability(
    @SerializedName("url") var url: String,
    @SerializedName("name") var name: String
)

data class Form(
    @SerializedName("url") var url: String,
    @SerializedName("name") var name: String
)

data class Stats(
    @SerializedName("stat") var stat: Stat,
    @SerializedName("effort") var effort: Int,
    @SerializedName("base_stat") var baseStat: Int
)

data class Stat(
    @SerializedName("url") var url: String,
    @SerializedName("name") var name: String
)

data class Sprites(
    @SerializedName("back_female") var backFemale: Any,
    @SerializedName("back_shiny_female") var backShinyFemale: Any,
    @SerializedName("back_default") var backDefault: String,
    @SerializedName("front_female") var frontFemale: Any,
    @SerializedName("front_shiny_female") var frontShinyFemale: Any,
    @SerializedName("back_shiny") var backShiny: String,
    @SerializedName("front_default") var frontDefault: String,
    @SerializedName("front_shiny") var frontShiny: String
)

data class GameIndice(
    @SerializedName("version") var version: Version,
    @SerializedName("game_index") var gameIndex: Int
)

data class Version(
    @SerializedName("url") var url: String,
    @SerializedName("name") var name: String
)

data class Moves(
    @SerializedName("version_group_details") var versionGroupDetails: List<VersionGroupDetail>,
    @SerializedName("move") var move: Move
)

data class VersionGroupDetail(
    @SerializedName("move_learn_method") var moveLearnMethod: MoveLearnMethod,
    @SerializedName("level_learned_at") var levelLearnedAt: Int,
    @SerializedName("version_group") var versionGroup: VersionGroup
)

data class MoveLearnMethod(
    @SerializedName("url") var url: String,
    @SerializedName("name") var name: String
)

data class VersionGroup(
    @SerializedName("url") var url: String,
    @SerializedName("name") var name: String
)

data class Move(
    @SerializedName("url") var url: String,
    @SerializedName("name") var name: String
)

data class Types(
    @SerializedName("slot") var slot: Int,
    @SerializedName("type") var type: Type
)

data class Type(
    @SerializedName("url") var url: String,
    @SerializedName("name") var name: String
)

data class HeldItem(
    @SerializedName("item") var item: Item,
    @SerializedName("version_details") var versionDetails: List<VersionDetail>
)

data class VersionDetail(
    @SerializedName("version") var version: Version,
    @SerializedName("rarity") var rarity: Int
)

data class Item(
    @SerializedName("url") var url: String,
    @SerializedName("name") var name: String
)

data class Species(
    @SerializedName("url") var url: String,
    @SerializedName("name") var name: String
)