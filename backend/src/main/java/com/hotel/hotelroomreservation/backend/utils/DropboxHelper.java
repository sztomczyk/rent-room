package com.hotel.hotelroomreservation.backend.utils;

import com.dropbox.core.DbxException;
import com.dropbox.core.DbxRequestConfig;
import com.dropbox.core.v1.DbxClientV1;
import com.dropbox.core.v1.DbxWriteMode;
import com.hotel.hotelroomreservation.backend.constants.Constants;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;

public final class DropboxHelper {
    private static final String ACCESS_TOKEN = "sl.BI-Zp4UcgCJGEnVVgjFJQsLHWrZgjNnVa0wXXuLiLlcPWDGXZM-H6IDJMG20gcByAi7vMtIpbhImq5AcoNs-6BmJ_7dpbLLuzyTs7SSvrlYSxvz3m4gY-jkFIgh0dDFRCK7_c-Y";
    private static final String CLIENT_IDENTIFIER = "dropbox/hotelroomsreservation";

    private static final DbxRequestConfig config = new DbxRequestConfig(CLIENT_IDENTIFIER);
    private static final DbxClientV1 client = new DbxClientV1(config, ACCESS_TOKEN);

    public InputStream getFileInputStream(final String fileName) {
        DbxClientV1.Downloader downloader = null;

        try {
            downloader = client.startGetFile(Constants.ROOT_DB_DIRECTORY + fileName + Constants.JSON_EXTENSION, null);
        } catch (final DbxException e) {
            e.printStackTrace();
        }

        if (downloader != null) {
            return downloader.body;
        }

        return null;
    }

    public void updateBookingsFile(final DataInputStream inputStream) {
        try {
            client.uploadFile(Constants.FILE_BOOKINGS_PATH, DbxWriteMode.update(null), -1, inputStream);
        } catch (DbxException | IOException e) {
            e.printStackTrace();
        }

    }
}
