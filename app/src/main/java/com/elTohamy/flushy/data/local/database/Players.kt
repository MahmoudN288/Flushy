package com.elTohamy.flushy.data.local.database

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Serializable

@Serializable
@Entity(tableName = "player_table")
data class PlayerInfoResponseItemEntity(
	@PrimaryKey(autoGenerate = true)
	val id: Int,
	@SerializedName("player")
	@Embedded(prefix = "player_info_")
	val player: Player,
	@SerializedName("statistics")
	@TypeConverters(DatabaseConverter::class)
	val statistics: List<StatisticsItem>
)

@Serializable
data class Player(
	@SerializedName("injured")
	val injured: Boolean,
	@SerializedName("firstname")
	val firstname: String,
	@SerializedName("nationality")
	val nationality: String,
	@SerializedName("name")
	val name: String,
	@SerializedName("birth")
	@Embedded(prefix = "player_birth_")
	val birth: Birth,
	@SerializedName("weight")
	val weight: String,
	@SerializedName("photo")
	val photo: String,
	@SerializedName("id")
	val id: Int,
	@SerializedName("age")
	val age: Int,
	@SerializedName("lastname")
	val lastname: String,
	@SerializedName("height")
	val height: String
)

@Serializable
data class Birth(
	@SerializedName("date")
	val date: String,
	@SerializedName("country")
	val country: String,
	@SerializedName("place")
	val place: String
)

@Serializable
data class StatisticsItem(
	@SerializedName("fouls")
	@Embedded(prefix = "player_fouls_")
	val fouls: Fouls,
	@SerializedName("cards")
	@Embedded(prefix = "player_cards_")
	val cards: Cards,
	@SerializedName("dribbles")
	@Embedded(prefix = "player_dribbles_")
	val dribbles: Dribbles,
	@SerializedName("substitutes")
	@Embedded(prefix = "player_substitutes_")
	val substitutes: Substitutes,
	@SerializedName("penalty")
	@Embedded(prefix = "player_penalty_")
	val penalty: Penalty,
	@SerializedName("league")
	@Embedded(prefix = "player_league_")
	val league: League,
	@SerializedName("team")
	@Embedded(prefix = "player_team_")
	val team: Team,
	@SerializedName("duels")
	@Embedded(prefix = "player_duels_")
	val duels: Duels,
	@SerializedName("passes")
	@Embedded(prefix = "player_passes_")
	val passes: Passes,
	@SerializedName("games")
	@Embedded(prefix = "player_games_")
	val games: Games,
	@SerializedName("tackles")
	@Embedded(prefix = "player_tackles_")
	val tackles: Tackles,
	@SerializedName("shots")
	@Embedded(prefix = "player_shots_")
	val shots: Shots,
	@SerializedName("goals")
	@Embedded(prefix = "player_goals_")
	val goals: Goals
)

@Serializable
data class Fouls(
	@SerializedName("committed")
	val committed: Int,
	@SerializedName("drawn")
	val drawn: Int
)

@Serializable
data class Cards(
	@SerializedName("red")
	val red: Int,
	@SerializedName("yellowred")
	val yellowred: Int,
	@SerializedName("yellow")
	val yellow: Int
)

@Serializable
data class Dribbles(
	@SerializedName("success")
	val success: Int,
	@SerializedName("past")
	val past: Int,
	@SerializedName("attempts")
	val attempts: Int
)

@Serializable
data class Substitutes(
	@SerializedName("bench")
	val bench: Int,
	@SerializedName("inL")
	val inL: Int,
	@SerializedName("out")
	val out: Int
)

@Serializable
data class Penalty(
	@SerializedName("saved")
	val saved: Int,
	@SerializedName("scored")
	val scored: Int,
	@SerializedName("missed")
	val missed: Int,
	@SerializedName("won")
	val won: Int,
	@SerializedName("commited")
	val commited: Int
)

@Serializable
data class League(
	@SerializedName("country")
	val country: String,
	@SerializedName("flag")
	val flag: String,
	@SerializedName("name")
	val name: String,
	@SerializedName("logo")
	val logo: String,
	@SerializedName("season")
	val season: Int,
	@SerializedName("id")
	val id: Int
)

@Serializable
data class Team(
	@SerializedName("name")
	val name: String,
	@SerializedName("logo")
	val logo: String,
	@SerializedName("id")
	val id: Int
)

@Serializable
data class Duels(
	@SerializedName("total")
	val total: Int,
	@SerializedName("won")
	val won: Int
)

@Serializable
data class Passes(
	@SerializedName("total")
	val total: Int,
	@SerializedName("accuracy")
	val accuracy: Int,
	@SerializedName("key")
	val key: Int
)

@Serializable
data class Games(
	@SerializedName("number")
	val number: Int,
	@SerializedName("minutes")
	val minutes: Int,
	@SerializedName("rating")
	val rating: Int,
	@SerializedName("appearances")
	val appearences: Int,
	@SerializedName("position")
	val position: String,
	@SerializedName("captain")
	val captain: Boolean,
	@SerializedName("lineups")
	val lineups: Int
)

@Serializable
data class Tackles(
	@SerializedName("total")
	val total: Int,
	@SerializedName("blocks")
	val blocks: Int,
	@SerializedName("interceptions")
	val interceptions: Int
)

@Serializable
data class Shots(
	@SerializedName("total")
	val total: Int,
	@SerializedName("on")
	val on: Int
)

@Serializable
data class Goals(
	@SerializedName("conceded")
	val conceded: Int,
	@SerializedName("total")
	val total: Int,
	@SerializedName("saves")
	val saves: Int,
	@SerializedName("assists")
	val assists: Int
)