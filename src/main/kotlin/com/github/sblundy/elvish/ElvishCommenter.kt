package com.github.sblundy.elvish

import com.intellij.lang.Commenter

class ElvishCommenter : Commenter {
    override fun getLineCommentPrefix(): String? = "#"
    override fun getBlockCommentPrefix(): String? = null
    override fun getBlockCommentSuffix(): String? = null
    override fun getCommentedBlockCommentPrefix(): String? = null
    override fun getCommentedBlockCommentSuffix(): String? = null
}