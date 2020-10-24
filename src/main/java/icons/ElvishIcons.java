package icons;

import com.intellij.openapi.util.IconLoader;

import javax.swing.*;

public interface ElvishIcons {
    Icon FILE_ICON = IconLoader.getIcon("/icons/fileIcon.svg", ElvishIcons.class);
    Icon BUILTIN_FUNCTION = IconLoader.getIcon("/icons/builtinFunction.svg", ElvishIcons.class);
    Icon BUILTIN_VARIABLE = IconLoader.getIcon("/icons/builtinVariable.svg", ElvishIcons.class);
    Icon BUILTIN_VALUE = IconLoader.getIcon("/icons/builtinValue.svg", ElvishIcons.class);
}
