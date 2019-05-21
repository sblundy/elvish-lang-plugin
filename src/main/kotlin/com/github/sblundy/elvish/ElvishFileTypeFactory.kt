package com.github.sblundy.elvish

import com.intellij.openapi.fileTypes.FileTypeConsumer
import com.intellij.openapi.fileTypes.FileTypeFactory

class ElvishFileTypeFactory: FileTypeFactory() {
    override fun createFileTypes(consumer: FileTypeConsumer) {
        consumer.consume(ElvishFileType.INSTANCE)
    }
}