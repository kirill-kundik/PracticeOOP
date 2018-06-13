package ua.com.kundikprojects.Lab5;

import javax.swing.filechooser.FileFilter;

//filter for pictures

class TextFileFilter extends FileFilter
{
    private String ext;
    public TextFileFilter(String ext)
    {
        this.ext=ext;
    }
    public boolean accept(java.io.File file)
    {
        if (file.isDirectory()) return true;
        return (file.getName().endsWith(ext));
    }
    public String getDescription()
    {
        return "*"+ext;
    }
}
