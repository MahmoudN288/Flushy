package com.elTohamy.flushy.presentation.customTheme

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color

@Immutable
data class CustomColorsPalette(
    //Main
    val blackToWhite: Color = Color.Unspecified,
    val titleCardColor: Color = Color.Unspecified,
    val mainAppBar: Color = Color.Unspecified,
    //Match Card
    val matchCard: Color = Color.Unspecified,
    val matchCardTextLive: Color = Color.Unspecified,
    val matchCardHeader: Color = Color.Unspecified,
    val matchCardTimeFinished: Color = Color.Unspecified,
    val matchCardFollowIcon: Color = Color.Unspecified,
    //Bottom Bar
    val bottomBarSelectedIcon: Color = Color.Unspecified,
    val bottomBarUnselectedIcon: Color = Color.Unspecified,
    val bottomBarSelectedIconBackground: Color = Color.Unspecified,
    //Premier League
    val mainCardContainer: Color = Color.Unspecified,
    val matchDetailsCardContainer: Color = Color.Unspecified,
    val logoTint: Color = Color.Unspecified,
    val appBarPL: Color = Color.Unspecified,
    val standingsRowsColor: Color = Color.Unspecified,
    //La Liga
    val matchHeaderBackgroundL: Color = Color.Unspecified,
    val matchHeaderTextL: Color = Color.Unspecified,
    val logoTintL: Color = Color.Unspecified,
    val appBarL: Color = Color.Unspecified,
    //Match Header Bundesliga
    val matchHeaderBackgroundBL: Color = Color.Unspecified,
    val matchHeaderTextBL: Color = Color.Unspecified,
    val logoTintBL: Color = Color.Unspecified,
    val appBarBL: Color = Color.Unspecified,
    //Match Header Ligue1
    val matchHeaderBackgroundL1: Color = Color.Unspecified,
    val matchHeaderTextL1: Color = Color.Unspecified,
    val logoTintL1: Color = Color.Unspecified,
    val appBarL1: Color = Color.Unspecified,
    //Match Header SerieA
    val matchHeaderBackgroundSA: Color = Color.Unspecified,
    val matchHeaderTextSA: Color = Color.Unspecified,
    val logoTintSA: Color = Color.Unspecified,
    val appBarSA: Color = Color.Unspecified,
    //Match Header EPL
    val matchHeaderBackgroundEPL: Color = Color.Unspecified,
    val matchHeaderTextEPL: Color = Color.Unspecified,
    val logoTintEPL: Color = Color.Unspecified,
    val appBarEPL: Color = Color.Unspecified,
    //Match Header ROSH
    val matchHeaderBackgroundROSH: Color = Color.Unspecified,
    val matchHeaderTextROSH: Color = Color.Unspecified,
    val logoTintROSH: Color = Color.Unspecified,
    val appBarROSH: Color = Color.Unspecified,
    //Match Header BOT
    val matchHeaderBackgroundBOT: Color = Color.Unspecified,
    val matchHeaderTextBOT: Color = Color.Unspecified,
    val logoTintBOT: Color = Color.Unspecified,
    val appBarBOT: Color = Color.Unspecified,
    //Match Header POR
    val matchHeaderBackgroundPOR: Color = Color.Unspecified,
    val matchHeaderTextPOR: Color = Color.Unspecified,
    val logoTintPOR: Color = Color.Unspecified,
    val appBarPOR: Color = Color.Unspecified,
    //Match Header TUR
    val matchHeaderBackgroundTUR: Color = Color.Unspecified,
    val matchHeaderTextTUR: Color = Color.Unspecified,
    val logoTintTUR: Color = Color.Unspecified,
    val appBarTUR: Color = Color.Unspecified,
    //Match Header BRA
    val matchHeaderBackgroundBRA: Color = Color.Unspecified,
    val matchHeaderTextBRA: Color = Color.Unspecified,
    val logoTintBRA: Color = Color.Unspecified,
    val appBarBRA: Color = Color.Unspecified,
    //Match Header FCWC
    val matchHeaderBackgroundFCWC: Color = Color.Unspecified,
    val matchHeaderTextFCWC: Color = Color.Unspecified,
    val logoTintFCWC: Color = Color.Unspecified,
    val appBarFCWC: Color = Color.Unspecified,
    //Match Header UCL
    val matchHeaderBackgroundUCL: Color = Color.Unspecified,
    val matchHeaderTextUCL: Color = Color.Unspecified,
    val logoTintUCL: Color = Color.Unspecified,
    val appBarUCL: Color = Color.Unspecified,
    //Match Header UEL
    val matchHeaderBackgroundUEL: Color = Color.Unspecified,
    val matchHeaderTextUEL: Color = Color.Unspecified,
    val logoTintUEL: Color = Color.Unspecified,
    val appBarUEL: Color = Color.Unspecified,
    //Match Header ECL
    val matchHeaderBackgroundECL: Color = Color.Unspecified,
    val matchHeaderTextECL: Color = Color.Unspecified,
    val logoTintECL: Color = Color.Unspecified,
    val appBarECL: Color = Color.Unspecified,
    //Match Header CAF
    val matchHeaderBackgroundCAF: Color = Color.Unspecified,
    val matchHeaderTextCAF: Color = Color.Unspecified,
    val logoTintCAF: Color = Color.Unspecified,
    val appBarCAF: Color = Color.Unspecified,
    //Match Header CONF
    val matchHeaderBackgroundCONF: Color = Color.Unspecified,
    val matchHeaderTextCONF: Color = Color.Unspecified,
    val logoTintCONF: Color = Color.Unspecified,
    val appBarCONF: Color = Color.Unspecified,
    //Match Header AFS
    val matchHeaderBackgroundAFS: Color = Color.Unspecified,
    val matchHeaderTextAFS: Color = Color.Unspecified,
    val logoTintAFS: Color = Color.Unspecified,
    val appBarAFS: Color = Color.Unspecified,
    //Match Header AFCCL
    val matchHeaderBackgroundAFCCL: Color = Color.Unspecified,
    val matchHeaderTextAFCCL: Color = Color.Unspecified,
    val logoTintAFCCL: Color = Color.Unspecified,
    val appBarAFCCL: Color = Color.Unspecified,
    //Match Header LIB
    val matchHeaderBackgroundLIB: Color = Color.Unspecified,
    val matchHeaderTextLIB: Color = Color.Unspecified,
    val logoTintLIB: Color = Color.Unspecified,
    val appBarLIB: Color = Color.Unspecified,
    //Match Header FWC
    val matchHeaderBackgroundFWC: Color = Color.Unspecified,
    val matchHeaderTextFWC: Color = Color.Unspecified,
    val logoTintFWC: Color = Color.Unspecified,
    val appBarFWC: Color = Color.Unspecified,
    //Match Header OLY
    val matchHeaderBackgroundOLY: Color = Color.Unspecified,
    val matchHeaderTextOLY: Color = Color.Unspecified,
    val logoTintOLY: Color = Color.Unspecified,
    val appBarOLY: Color = Color.Unspecified,
    //Match Header EURO
    val matchHeaderBackgroundEU: Color = Color.Unspecified,
    val matchHeaderTextEU: Color = Color.Unspecified,
    val logoTintEU: Color = Color.Unspecified,
    val appBarEU: Color = Color.Unspecified,
    //Match Header UNL
    val matchHeaderBackgroundUNL: Color = Color.Unspecified,
    val matchHeaderTextUNL: Color = Color.Unspecified,
    val logoTintUNL: Color = Color.Unspecified,
    val appBarUNL: Color = Color.Unspecified,
    //Match Header AFCON
    val matchHeaderBackgroundAFCON: Color = Color.Unspecified,
    val matchHeaderTextAFCON: Color = Color.Unspecified,
    val logoTintAFCON: Color = Color.Unspecified,
    val appBarAFCON: Color = Color.Unspecified,
    //Match Header CopaAmerica
    val matchHeaderBackgroundCopaAmerica: Color = Color.Unspecified,
    val matchHeaderTextCopaAmerica: Color = Color.Unspecified,
    val logoTintCopaAmerica: Color = Color.Unspecified,
    val appBarCopaAmerica: Color = Color.Unspecified,
    //Match Header AFC
    val matchHeaderBackgroundAFC: Color = Color.Unspecified,
    val matchHeaderTextAFC: Color = Color.Unspecified,
    val logoTintAFC: Color = Color.Unspecified,
    val appBarAFC: Color = Color.Unspecified,
    //Match Header CON
    val matchHeaderBackgroundCON: Color = Color.Unspecified,
    val matchHeaderTextCON: Color = Color.Unspecified,
    val logoTintCON: Color = Color.Unspecified,
    val appBarCON: Color = Color.Unspecified,
)



//Main
val LightBlackToWhiteColor = Color(color = 0xFF000000)
val LightTitleCardColor = Color(color = 0xFFFFFFFF)
val LightMainAppBar: Color = Color(color = 0xFFFFFFFF)
//Match Card
val LightMatchCard = Color(color = 0xFFFFFFFF)
val LightMatchCardTextLive = Color(color = 0xFF000000)
val LightMatchCardHeader = Color(color = 0xFF939496)
val LightMatchCardTimeFinished = Color(color = 0xFF4B4242)
val LightMatchCardFollowIcon = Color(color = 0xFF2011EE)
//Bottom Bar
val LightBottomBarSelectedIcon = Color(color = 0xFFFFFFFF)
val LightBottomBarUnselectedIcon = Color(color = 0xFF000000)
val LightBottomBarSelectedIconBackground = Color(color = 0xFF302988)
//PL
val LightMainCardContainer = Color(color = 0xFFFFFFFF)
val LightMatchDetailsCardContainer: Color = Color(color = 0xFFFFFFFF)
val LightMatchHeaderLogoTintPL: Color = Color(color = 0xFF390E38)
val LightAppBarPL: Color = Color(color = 0xFFFFFFFF)
val LightPlStandingsRowsColor: Color = Color(color = 0xFFFFFFFF)
//La Liga
val LightMatchHeaderBackgroundL = Color(color = 0xFFFE010B)
val LightMatchHeaderTextL: Color = Color(color = 0xFF000000)
val LightMatchHeaderLogoTintL: Color = Color(color = 0xFF336118)
val LightAppBarL: Color = Color(color = 0xFFD1484D)





//Main
//Main
val DarkBlackToWhiteColor = Color(color = 0xFFFFFFFF)
val DarkTitleCardColor = Color(color = 0xFF463D5C)
val DarkMainAppBar: Color = Color(color = 0xFF2B2E3C)
//Match Card
val DarkMatchCard = Color(color = 0xFF252525)
val DarkMatchCardTextLive = Color(color = 0xFFFFFFFF)
val DarkMatchCardHeader = Color(color = 0xFF303131)
val DarkMatchCardTimeFinished = Color(color = 0xFFF7BEBD)
val DarkMatchCardFollowIcon = Color(color = 0xFF584EE9)
//Bottom Bar
val DarkBottomBarSelectedIcon = Color(color = 0xFFFFFFFF)
val DarkBottomBarUnselectedIcon = Color(color = 0xFFFFFFFF)
val DarkBottomBarSelectedIconBackground = Color(color = 0xFF9A93FC)
//PL
val DarkMainCardContainer = Color(color = 0xFF2B2E3C)
val DarkMatchDetailsCardContainer: Color = Color(color = 0xFF2B2E3C)
val DarkLogoTintPL: Color = Color(color = 0xFFFFFFFF)
val DarkAppBarPL: Color = Color(color = 0xFF390E38)
val DarkPlStandingsRowsColor: Color = Color(color = 0xFF252424)
//La Liga
val DarkMatchHeaderBackgroundL = Color(color = 0xFFFFFFFF)
val DarkMatchHeaderTextL: Color = Color(color = 0xFF390E38)
val DarkLogoTintL: Color = Color(color = 0xFFFE010B)
val DarkAppBarL: Color = Color(color = 0xFF000000)







val LightCustomColorsPalette = CustomColorsPalette(
    //Main
    blackToWhite = LightBlackToWhiteColor,
    titleCardColor = LightTitleCardColor,
    mainAppBar = LightMainAppBar,
    //Match Card
    matchCard = LightMatchCard,
    matchCardTextLive = LightMatchCardTextLive,
    matchCardHeader = LightMatchCardHeader,
    matchCardTimeFinished = LightMatchCardTimeFinished,
    matchCardFollowIcon = LightMatchCardFollowIcon,
    //Bottom Bar
    bottomBarSelectedIcon = LightBottomBarSelectedIcon,
    bottomBarUnselectedIcon = LightBottomBarUnselectedIcon,
    bottomBarSelectedIconBackground = LightBottomBarSelectedIconBackground,
    //PL
    mainCardContainer = LightMainCardContainer,
    matchDetailsCardContainer = LightMatchDetailsCardContainer,
    logoTint = LightMatchHeaderLogoTintPL,
    appBarPL = LightAppBarPL,
    standingsRowsColor = LightPlStandingsRowsColor,
    //La Liga
    matchHeaderBackgroundL = LightMatchHeaderBackgroundL,
    matchHeaderTextL = LightMatchHeaderTextL,
    logoTintL = LightMatchHeaderLogoTintL,
    appBarL = LightAppBarL
)




val DarkCustomColorsPalette = CustomColorsPalette(
    //Main
    blackToWhite = DarkBlackToWhiteColor,
    titleCardColor = DarkTitleCardColor,
    mainAppBar = DarkMainAppBar,
    //Match Card
    matchCard = DarkMatchCard,
    matchCardTextLive = DarkMatchCardTextLive,
    matchCardHeader = DarkMatchCardHeader,
    matchCardTimeFinished = DarkMatchCardTimeFinished,
    matchCardFollowIcon = DarkMatchCardFollowIcon,
    //Bottom Bar
    bottomBarSelectedIcon = DarkBottomBarSelectedIcon,
    bottomBarUnselectedIcon = DarkBottomBarUnselectedIcon,
    bottomBarSelectedIconBackground = DarkBottomBarSelectedIconBackground,
    //PL
    mainCardContainer = DarkMainCardContainer,
    matchDetailsCardContainer = DarkMatchDetailsCardContainer,
    logoTint = DarkLogoTintPL,
    appBarPL = DarkAppBarPL,
    standingsRowsColor = DarkPlStandingsRowsColor,
    //La Liga
    matchHeaderBackgroundL = DarkMatchHeaderBackgroundL,
    matchHeaderTextL = DarkMatchHeaderTextL,
    logoTintL = DarkLogoTintL,
    appBarL = DarkAppBarL
)

val MaterialTheme.customColorsPalette: CustomColorsPalette
    @Composable
    @ReadOnlyComposable
    get() = LocalCustomColorsPalette.current

val LocalCustomColorsPalette = staticCompositionLocalOf { CustomColorsPalette() }