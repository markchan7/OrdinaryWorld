package com.markchan.carrier.data.cache;

import android.text.TextUtils;

import com.blankj.utilcode.util.FileUtils;
import com.markchan.carrier.Scheme;
import com.markchan.carrier.data.dao.FontEntityDao;
import com.markchan.carrier.data.entity.FontEntity;
import com.markchan.carrier.domain.Font;

import java.util.Iterator;
import java.util.List;

/**
 * Created by Mark on 2017/7/16.
 */
public class FontEntityCacheImpl implements FontEntityCache {

    private final FontEntityDao mFontEntityDao;

    @Override
    public boolean setFontDownloaded(Font font) {
        return false;
    }

    @Override
    public boolean deleteDownloadedFont(Font font) {
        return false;
    }

    @Override
    public void deleteAllDownloadedFonts() {

    }

    // DOMAIN
    // ---------------------------------------------------------------------------------------------
    // DATA

    public FontEntityCacheImpl(FontEntityDao fontEntityDao) {
        if (fontEntityDao == null) {
            throw new NullPointerException("Context or Font DAO can't be null");
        }
        mFontEntityDao = fontEntityDao;
    }

    @Override
    public FontEntity getDownloadedFontEntity(int fontId) {
        final FontEntity fontEntity = mFontEntityDao.queryFontEntityById(fontId);
        if (fontEntity != null) {
            if (!FileUtils.isFileExists(Scheme.FILE.crop(fontEntity.getUri()))) {
                fontEntity.setUri(fontEntity.getUrl());
                fontEntity.save();
                return null;
            }
        }
        return fontEntity;
    }

    @Override
    public List<FontEntity> getDownloadedFontEntities() {
        final List<FontEntity> fontEntities = mFontEntityDao.queryDownloadedFontEntities();
        Iterator<FontEntity> iterator = fontEntities.iterator();
        while (iterator.hasNext()) {
            FontEntity fontEntity = iterator.next();
            if (!FileUtils.isFileExists(Scheme.FILE.crop(fontEntity.getUri()))) {
                fontEntity.setUri(fontEntity.getUrl());
                mFontEntityDao.updateFontEntity(fontEntity);
                iterator.remove();
            }
        }
        return fontEntities;
    }

    @Override
    public boolean setFontEntityDownloaded(final FontEntity fontEntity) {
        if (!isFontEntityExists(fontEntity)) {
            fontEntity.setUri(fontEntity.getUrl());
        }
        return mFontEntityDao.insertOrUpdateFontEntity(fontEntity);
    }

    @Override
    public boolean deleteDownloadedFontEntity(FontEntity fontEntity) {
        return false;
    }

    private boolean isFontEntityExists(FontEntity fontEntity) {
        if (fontEntity != null) {
            String uri = fontEntity.getUri();
            if (TextUtils.isEmpty(uri)) {
                if (Scheme.ofUri(uri) == Scheme.FILE) {
                    return FileUtils.isFileExists(Scheme.FILE.crop(uri));
                }
            }
        }
        return false;
    }

    @Override
    public boolean isDownloaded(int fontId) {
        FontEntity fontEntity = mFontEntityDao.queryFontEntityById(fontId);
        return isFontEntityExists(fontEntity);
    }

    @Override
    public void deleteAllDownloadedFontEntities() {
        List<FontEntity> fontEntities = mFontEntityDao.queryDownloadedFontEntities();
        if (fontEntities != null && !fontEntities.isEmpty()) {
            for (FontEntity fontEntity : fontEntities) {
                if (FileUtils.deleteFile(Scheme.FILE.crop(fontEntity.getUri()))) {
                    fontEntity.setUri(fontEntity.getUrl());
                    fontEntity.update();
                }
            }
        }
    }
}