package com.elTohamy.flushy.utils

object Constants {
    //Application Tag
    const val TAG = "TAG"
    //Sports API Constants and Parameters
    const val BASE_URL = "https://v3.football.api-sports.io/"

    //Leagues
    const val GET_LEAGUES = "leagues"
    const val GET_LEAGUES_SEASONS = "leagues/seasons"
    //Timezone
    const val GET_TIMEZONE = "timezone"
    //Countries
    const val GET_COUNTRIES = "countries"
    //Teams
    const val GET_TEAMS_INFO = "teams"
    const val GET_TEAMS_STATS = "teams/statistics"
    const val GET_TEAMS_SEASONS = "teams/seasons"
    const val GET_TEAMS_COUNTRIES = "teams/countries"
    //Venues
    const val GET_VENUES = "venues"
    //Standings
    const val GET_STANDINGS = "standings"
    //Fixtures
    const val GET_FIXTURES = "fixtures"
    const val GET_FIXTURES_ROUNDS = "fixtures/rounds"
    const val GET_FIXTURES_HEADTOHEAD = "fixtures/headtohead"
    const val GET_FIXTURES_STATISTICS = "fixtures/statistics"
    const val GET_FIXTURES_EVENTS = "fixtures/events"
    const val GET_FIXTURES_LINEUPS = "fixtures/lineups"
    const val GET_FIXTURES_PLAYERS_STATS = "fixtures/players"
    //Injuries
    const val GET_INJURIES = "injuries"
    //Predictions
    const val GET_PREDICTIONS = "predictions"
    //Coaches
    const val GET_COACHS = "coachs"
    //Players
    const val GET_PLAYERS = "players"
    const val GET_PLAYER_SEASONS = "players/seasons"
    const val GET_PLAYER_SQUADS = "players/squads"
    const val GET_TOP_SCORERS = "players/topscorers"
    const val GET_TOP_ASSISTS = "players/topassists"
    const val GET_TOP_YELLOW_CARDS = "players/topyellowcards"
    const val GET_TOP_RED_CARDS = "players/topredcards"
    //Transfers
    const val GET_TRANSFERS = "transfers"
    //Trophies
    const val GET_TROPHIES = "trophies"
    //Sidelined
    const val GET_SIDELINED = "sidelined"
    //Odds
    const val GET_ODDS = "odds"
    const val GET_MAPPING = "odds/mapping"
    const val GET_BOOKMAKERS = "odds/bookmakers"
    const val GET_BETS = "odds/bets"
    const val GET_LIVE_ODDS = "odds/live"
    const val GET_LIVE_ODDS_BETS = "odds/live/bets"


    //Parameters
    //Leagues
    const val LEAGUES_ID_PARAM = "id"
    const val LEAGUES_NAME_PARAM = "name"
    const val LEAGUES_BY_COUNTRY_NAME_PARAM = "country"
    const val LEAGUES_BY_CODE_PARAM = "code"
    const val LEAGUES_BY_SEASON_PARAM = "season"
    const val LEAGUES_BY_TEAM_PARAM = "team"
    const val LEAGUES_BY_TYPE_PARAM = "type"
    const val LEAGUES_BY_CURRENT_PARAM = "current"
    const val LEAGUES_BY_SEARCH_PARAM = "search"
    const val LEAGUES_BY_LAST_PARAM = "last"
    //No Seasons Parameters
    //No Timezone Parameters
    //Countries
    const val COUNTRIES_BY_COUNTRY_NAME_PARAM = "name"
    const val COUNTRIES_BY_COUNTRY_CODE_PARAM = "code"
    const val COUNTRIES_BY_SEARCH_NAME_PARAM = "search"
    //Teams
    const val TEAMS_BY_ID_PARAM = "id"
    const val TEAMS_BY_NAME_PARAM = "name"
    const val TEAMS_BY_LEAGUE_ID_PARAM= "league"
    const val TEAMS_BY_SEASON_PARAM = "season"
    const val TEAMS_BY_COUNTRY_NAME_PARAM = "country"
    const val TEAMS_BY_CODE_PARAM = "code"
    const val TEAMS_BY_VENUE_ID_PARAM = "venue"
    const val TEAMS_BY_SEARCH_NAME_OR_COUNTRY_NAME_PARAM = "search"
    //Teams Statistics
    const val TEAMS_STATS_BY_LEAGUE_ID_PARAM = "league"
    const val TEAMS_STATS_BY_SEASON_PARAM = "season"
    const val TEAMS_STATS_BY_TEAM_ID_PARAM = "team"
    const val TEAMS_STATS_BY_DATE_PARAM = "date"
    //Teams Seasons
    const val TEAMS_SEASONS_BY_ID_PARAM = "team"
    //No Teams Countries Parameters
    //Venue
    const val VENUE_BY_ID_PARAM = "id"
    const val VENUE_BY_NAME_PARAM = "name"
    const val VENUE_BY_CITY_PARAM = "city"
    const val VENUE_BY_COUNTRY_PARAM = "country"
    const val VENUE_BY_SEARCH_NAME_CITY_COUNTRY_PARAM = "search"
    //Standings
    const val STANDINGS_BY_LEAGUE_ID_PARAM = "league"
    const val STANDINGS_BY_SEASON_PARAM = "season"
    const val STANDINGS_BY_TEAM_ID_PARAM = "team"
    //Fixtures
    const val FIXTURES_BY_ID_PARAM = "id"
    const val FIXTURES_BY_IDS_PARAM = "ids"
    const val FIXTURES_BY_LIVE_PARAM = "live"
    const val FIXTURES_BY_DATE_PARAM = "date"
    const val FIXTURES_BY_LEAGUE_ID_PARAM = "league"
    const val FIXTURES_BY_SEASON_PARAM = "season"
    const val FIXTURES_BY_TEAM_ID_PARAM = "team"
    const val FIXTURES_BY_LAST_PARAM = "last"
    const val FIXTURES_BY_NEXT_PARAM = "next"
    const val FIXTURES_BY_FROM_PARAM = "from"
    const val FIXTURES_BY_TO_PARAM = "to"
    const val FIXTURES_BY_ROUND_PARAM = "round"
    const val FIXTURES_BY_STATUS_PARAM = "status"
    const val FIXTURES_BY_VENUE_ID_PARAM = "venue"
    const val FIXTURES_BY_TIMEZONE_PARAM = "timezone"
    //Fixtures Rounds
    const val FIXTURES_ROUNDS_BY_LEAGUE_ID_PARAM = "league"
    const val FIXTURES_ROUNDS_BY_SEASON_PARAM = "season"
    const val FIXTURES_ROUNDS_BY_CURRENT_PARAM = "current"
    //Fixtures Head2Head
    const val FIXTURES_HEAD_TO_HEAD_BY_IDS_PARAM = "h2h"
    const val FIXTURES_HEAD_TO_HEAD_BY_DATE_PARAM = "date"
    const val FIXTURES_HEAD_TO_HEAD_LEAGUE_ID_PARAM = "league"
    const val FIXTURES_HEAD_TO_HEAD_BY_SEASON_PARAM = "season"
    const val FIXTURES_HEAD_TO_HEAD_BY_LAST_PARAM = "last"
    const val FIXTURES_HEAD_TO_HEAD_BY_NEXT_PARAM = "next"
    const val FIXTURES_HEAD_TO_HEAD_BY_FROM_PARAM = "from"
    const val FIXTURES_HEAD_TO_HEAD_BY_TO_PARAM = "to"
    const val FIXTURES_HEAD_TO_HEAD_BY_STATUS_PARAM = "status"
    const val FIXTURES_HEAD_TO_HEAD_BY_VENUE_PARAM = "venue"
    const val FIXTURES_HEAD_TO_HEAD_BY_TIMEZONE_PARAM = "timezone"
    //Fixtures Statistics
    const val FIXTURES_STATS_BY_FIXTURE_ID_PARAM = "fixture"
    const val FIXTURES_STATS_BY_TEAM_ID_PARAM = "team"
    const val FIXTURES_STATS_BY_TYPE_PARAM = "type"
    //Fixtures EVENTS
    const val FIXTURES_EVENT_BY_FIXTURE_ID_PARAM = "fixture"
    const val FIXTURES_EVENT_BY_TEAM_ID_PARAM = "team"
    const val FIXTURES_EVENT_BY_PLAYER_ID_PARAM = "player"
    const val FIXTURES_EVENT_BY_TYPE_PARAM = "type"
    //Fixtures Lineups
    const val FIXTURES_LINEUPS_BY_FIXTURE_ID_PARAM = "fixture"
    const val FIXTURES_LINEUPS_BY_TEAM_ID_PARAM = "team"
    const val FIXTURES_LINEUPS_BY_PLAYER_ID_PARAM = "player"
    const val FIXTURES_LINEUPS_BY_TYPE_PARAM = "type"
    //Fixtures Players' Stats
    const val FIXTURES_PLAYERS_STATS_BY_FIXTURE_ID_PARAM = "fixture"
    const val FIXTURES_PLAYERS_STATS_BY_TEAM_ID_PARAM = "team"
    //Injuries
    const val INJURIES_BY_LEAGUE_ID_PARAM = "league"
    const val INJURIES_BY_SEASON_PARAM = "season"
    const val INJURIES_BY_FIXTURE_ID_PARAM = "fixture"
    const val INJURIES_BY_TEAM_ID_PARAM = "team"
    const val INJURIES_BY_PLAYER_ID_PARAM = "player"
    const val INJURIES_BY_DATE_PARAM = "date"
    const val INJURIES_BY_TIMEZONE_PARAM = "timezone"
    //Predictions
    const val PREDICTIONS_BY_FIXTURE_ID_PARAM = "fixture"
    //Coaches
    const val COACHES_BY_COACH_ID_PARAM = "id"
    const val COACHES_BY_TEAM_ID_PARAM = "team"
    const val COACHES_BY_SEARCH_PARAM = "search"
    //Players
    const val PLAYERS_BY_PLAYER_ID_PARAM = "id"
    const val PLAYERS_BY_TEAM_ID_PARAM = "team"
    const val PLAYERS_BY_LEAGUE_ID_PARAM = "league"
    const val PLAYERS_BY_SEASON_PARAM = "season"
    const val PLAYERS_BY_SEARCH_PARAM = "search"
    const val PLAYERS_BY_PAGE_PARAM = "page"
    //Players Seasons
    const val PLAYERS_SEASONS_BY_PLAYER_ID_PARAM = "player"
    //Players Squads
    const val PLAYERS_SQUADS_BY_TEAM_ID_PARAM = "team"
    const val PLAYERS_SQUADS_BY_PLAYER_ID_PARAM = "player"
    //Players Top-Scorers
    const val PLAYERS_TOP_SCORERS_BY_LEAGUE_ID_PARAM = "league"
    const val PLAYERS_TOP_SCORERS_BY_SEASON_PARAM = "season"
    //Players Top-Assists
    const val PLAYERS_TOP_ASSISTS_BY_LEAGUE_ID_PARAM = "league"
    const val PLAYERS_TOP_ASSISTS_BY_SEASON_PARAM = "season"
    //Players Top-Yellow-Cards
    const val PLAYERS_TOP_YELLOW_CARDS_BY_LEAGUE_ID_PARAM = "league"
    const val PLAYERS_TOP_YELLOW_CARDS_BY_SEASON_PARAM = "season"
    //Players Top-Red-Cards
    const val PLAYERS_TOP_RED_CARDS_BY_LEAGUE_ID_PARAM = "league"
    const val PLAYERS_TOP_RED_CARDS_BY_SEASON_PARAM = "season"
    //Transfers
    const val TRANSFERS_BY_PLAYER_ID_PARAM = "player"
    const val TRANSFERS_BY_TEAM_ID_PARAM = "team"
    //Trophies
    const val TROPHIES_BY_PLAYER_ID_PARAM = "player"
    const val TROPHIES_BY_COACH_ID_PARAM = "coach"
    //Sidelined (Suspension)
    const val SIDELINED_BY_PLAYER_ID_PARAM = "player"
    const val SIDELINED_BY_COACH_ID_PARAM = "coach"
    //Odds
    const val ODDS_BY_FIXTURE_ID_PARAM = "fixture"
    const val ODDS_BY_LEAGUE_ID_PARAM = "league"
    const val ODDS_BY_SEASON_PARAM = "season"
    const val ODDS_BY_DATE_PARAM = "date"
    const val ODDS_BY_TIMEZONE_PARAM = "timezone"
    const val ODDS_BY_PAGE_PARAM = "page"
    const val ODDS_BY_BOOKMAKER_ID_PARAM = "bookmaker"
    const val ODDS_BY_BET_ID_PARAM = "bet"
    //Odds Mapping
    const val ODDS_MAPPING_BY_PAGE_PARAM = "page"
    //Odds Bookmakers
    const val ODDS_BOOKMAKER_BY_ID_PARAM = "id"
    const val ODDS_BOOKMAKER_BY_SEARCH_PARAM = "search"
    //Odds Bets
    const val ODDS_BET_BY_ID_PARAM = "id"
    const val ODDS_BET_BY_SEARCH_PARAM = "search"
    //Odds Live
    const val ODDS_LIVE_BY_FIXTURE_ID_PARAM = "fixture"
    const val ODDS_LIVE_BY_LEAGUE_ID_PARAM = "league"
    const val ODDS_LIVE_BY_BET_ID_PARAM = "bet"
    //Odds Live Bets
    const val ODDS_LIVE_BET_BY_ID_PARAM = "id"
    const val ODDS_LIVE_BET_BY_SEARCH_PARAM = "search"


    //News API Constants and Parameters
    const val NEWS_BASE_URL = "https://gnews.io/api/v4/search"
    const val NEWS_API = "apikey"
    const val NEWS_QUERY = "q"
    const val NEWS_LANGUAGE = "lang"
    const val NEWS_CATEGORY = "category"
    const val NEWS_COUNTRY = "country"
    const val NEWS_FROM_DATE = "from"
    const val NEWS_TO_DATE = "to"





    //Leagues Ids
    //World
    const val FWC = 1
    const val FCWC = 15
    //FWC_QU
    const val FWC_Q_AF = 29
    const val FWC_Q_AS = 30
    const val FWC_Q_CON = 31
    const val FWC_Q_EU = 32
    const val FWC_Q_OC = 33
    const val FWC_Q_SA = 34
    //Continent
    const val EURO = 4
    const val UNL = 5
    const val AFCON = 6
    const val ASIAN_CUP = 7
    const val FWC_W = 8
    const val COPA_AMERICA = 9
    const val FRIENDLIES = 10
    const val CONMEBOL_UEFA_FIN = 913
    const val ARAB_CUP = 860
    //Continental Qualifications
    const val EURO_QUALIFIERS = 960
    const val ASIAN_CUP_Q = 35
    const val AFCON_Q = 36
    const val FWC_Q_PLAY_OFFS = 37
    //F
    const val INT_CH_CUP = 26
    //Europa
    const val UCL = 2
    const val UEL = 3
    const val UEFA_CONF = 848
    const val USC = 531
    //Europa Youth
    const val UEFA_YOUTH = 14
    const val UEFA_U_21 = 38
    const val UEFA_U_19 = 493
    const val UEFA_U_17 = 921
    //Europa Women
    const val UCL_W = 525
    const val U_CH_W = 743
    //Africa
    const val CAF_CL = 12
    const val AFRICAN_NATIONS_C = 19
    const val CONFEDERATION = 20
    const val CAF_SUPER_CUP = 533
    const val AFCON_U_23 = 1015
    //South America
    const val LIBERTADORES = 13
    //CONCACAF
    const val CONMEBOL = 11
    const val CON_CL = 16
    const val CON_GOLD_CUP = 22
    //AFC
    const val AFC_CL = 17
    const val AFC_CUP = 18
    //England
    const val PREMIER_LEAGUE_ID = 39
    const val CHAMPIONSHIP = 40
    const val FA_CUP = 45
    const val EFL = 46
    const val LEAGUE_CUP = 48
    const val COMMUNITY_SHIELD = 528
    //Spain
    const val LA_LIGA = 140
    const val COPA_DEL_REY = 143
    const val SUPER_CUP_SPAIN = 556
    //Italy
    const val SERIE_A = 135
    const val COPPA_ITALIA = 137
    const val SUPER_CUP_ITALY = 547
    //Germany
    const val BUNDESLIGA = 78
    const val DFB_POKAL = 81
    const val SUPER_CUP_GERMANY = 529
    //France
    const val LIGUE_1 = 61
    const val COPPA_DE_LA_LIGUE = 65
    const val COUPE_DE_FRANCE = 66
    //Portugal
    const val PRIMEIRA_LIGA = 94
    const val TOCA_DE_LA_LIGA = 97
    const val SUPER_CUP_POR = 550
    //Turkey
    const val SUPER_LIG = 203
    const val CUP_TUR = 205
    const val SUPER_CUP_TUR = 551
    //Netherlands
    const val EREDIVISIE = 88
    //Belgium
    const val JUPILER = 144
    //Brazil
    const val SERIE_A_BRA = 71
    const val COPA_DE_BRA = 73
    //Argentina
    const val LIGA_ARG = 128
    const val COPA_ARG = 130
    //Saudi Arabia
    const val PRO_LEAGUE_SAU = 307
    const val KINGS_CUP = 504
    const val SUPER_CUP_SAU = 826
    //Egypt
    const val PREMIER_LEAGUE_EGY = 233
    const val EGYPT_CUP = 714
    const val EGYPT_SUPER_CUP = 539
    //Tunisia
    const val LIGUE_1_TUN = 202
    const val TUN_CUP = 511
    //Morocco
    const val BOTOLA_PRO = 200
    const val MOR_CUP = 822





    //Teams Ids
    //England
    const val ENGLAND_NT = 10
    const val MAN_UNITED = 33
    const val NEWCASTLE = 34
    const val LIVERPOOL = 40
    const val ARSENAL = 42
    const val TOTTENHAM = 47
    const val CHELSEA = 49
    const val MAN_CITY = 50
    const val ASTON_VILLA = 66
    //Spain
    const val SPAIN_NT = 9
    const val BARCELONA = 529
    const val ATLETICO_MADRID = 530
    const val ATHLETIC_CLUB = 531
    const val VALENCIA = 532
    const val VILLAREAL = 533
    const val SEVILLA = 536
    const val REAL_MADRID = 541
    const val REAL_SOCIEDAD = 548
    //Italy
    const val ITALY_NT = 768
    const val LAZIO = 487
    const val AC_MILAN = 489
    const val NAPOLI = 492
    const val JUVENTUS = 496
    const val ROMA = 497
    const val INTER = 505
    //Germany
    const val GERMANY_NT = 25
    const val BAYERN = 157
    const val BOR_MON = 163
    const val BOR_DOR = 165
    const val BAY_LIV = 168
    const val FRANC = 169
    const val LEIPZIG = 173
    //France
    const val FRANCE_NT = 2
    const val LYON = 80
    const val MARS = 81
    const val PSG = 85
    //Portugal
    const val POR_NT = 27
    const val BENFICA = 211
    const val PORTO = 212
    const val SPORTING = 228
    //Netherlands
    const val NET_NT = 1118
    const val AJAX = 194
    //Saudi Arabia
    const val SAUDI_NT = 23
    const val KHALEEJ = 2928
    const val AL_AHLY_JEDDAH = 2929
    const val AL_HILAL = 2932
    const val AL_ITTIHAD = 2938
    const val AL_NASSR = 2939
    //Inter Miami
    const val INTER_MIAMI = 9568
    //Brazil
    const val BRAZIL_NT = 6
    const val INT_BRA = 119
    const val PAL_BRA = 121
    const val FLU_BRA = 124
    const val SAO_BRA = 126
    const val FLA_BRA = 127
    const val SAN_BRA = 128
    //Argentina
    const val ARGENTINA_NT = 26
    const val RIVER_PLATE = 435
    const val BOCA_JUN = 451
    //Egypt
    const val EGYPT_NT = 32
    const val AL_AHLY_EGY = 1029
    const val AL_ISMAILY_EGY = 1030
    const val BYRAMIDS_EGY = 1036
    const val ZAMALEK_EGY = 1040
    //Algeria
    const val ALGERIA_NT = 1532
    const val BEL_ALG = 904
    const val SET_ALG = 905
    const val MC_ALG = 906
    const val USM_ALG = 910
    //Morocco
    const val MOROCCO_NT = 31
    const val RAJ_MOR = 966
    const val WYD_MOR = 968
    //Tunisia
    const val TUNISIA_NT = 28
    const val ES_TUN = 980
    const val SFA_TUN = 983
    const val ES_SAHEL_TUN = 990
    //Europa
    const val BELGIUM = 1
    const val CROATIA = 3
    const val DENMARK = 21
    //Africa
    const val CAMEROON = 1530
    const val GHANA = 1504
    const val IVORY_COAST = 1501
    const val NIGERIA = 19
    const val SENEGAL = 13
    const val SOUTH_AF = 1531
    //Asia
    const val IRAN = 22
    const val IRAQ = 1567
    const val JAPAN = 12
    const val JORDAN = 1548
    const val PALESTINE = 1562
    const val SOUTH_KOREA = 17
    //CON
    const val URUGUAY = 7
    //CONCACAF
    const val MEXICO = 16
}