package com.elTohamy.flushy.domain.use_case.emojis

import com.elTohamy.flushy.domain.use_case.emojis.AddReact
import com.elTohamy.flushy.domain.use_case.emojis.DeleteReact
import com.elTohamy.flushy.domain.use_case.emojis.GetAngryReact
import com.elTohamy.flushy.domain.use_case.emojis.GetCoolReact
import com.elTohamy.flushy.domain.use_case.emojis.GetLoveReact
import com.elTohamy.flushy.domain.use_case.emojis.GetPartyReact
import com.elTohamy.flushy.domain.use_case.emojis.GetReact
import com.elTohamy.flushy.domain.use_case.emojis.GetSadReact
import com.elTohamy.flushy.domain.use_case.emojis.UpdateReact

data class EmojisUseCases(
    val getReact: GetReact,
    val getLoveReacts: GetLoveReact,
    val getPartyReact: GetPartyReact,
    val getCoolReact: GetCoolReact,
    val getSadReact: GetSadReact,
    val getAngryReact: GetAngryReact,
    val addReact: AddReact,
    val updateReact: UpdateReact,
    val deleteReact: DeleteReact
)