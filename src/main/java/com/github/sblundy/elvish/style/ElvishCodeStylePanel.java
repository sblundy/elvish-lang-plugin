package com.github.sblundy.elvish.style;

import com.github.sblundy.elvish.ElvishLanguage;
import com.intellij.application.options.TabbedLanguageCodeStylePanel;
import com.intellij.psi.codeStyle.CodeStyleSettings;

import javax.swing.*;

public class ElvishCodeStylePanel extends TabbedLanguageCodeStylePanel {
    private JPanel myPreviewContainer;

    protected ElvishCodeStylePanel(CodeStyleSettings currentSettings, CodeStyleSettings settings) {
        super(ElvishLanguage.INSTANCE, currentSettings, settings);
        installPreviewPanel(myPreviewContainer);
    }

    @Override
    protected void initTabs(CodeStyleSettings settings) {
        addIndentOptionsTab(settings);
    }
}
