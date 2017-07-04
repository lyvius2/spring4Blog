package com.walter.util;

import com.drew.imaging.ImageMetadataReader;
import com.drew.imaging.ImageProcessingException;
import com.drew.metadata.Metadata;
import com.drew.metadata.exif.ExifIFD0Descriptor;
import com.drew.metadata.exif.ExifIFD0Directory;
import com.drew.metadata.exif.ExifSubIFDDescriptor;
import com.drew.metadata.exif.ExifSubIFDDirectory;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.InputStream;

/**
 * Created by yhwang131 on 2017-05-12.
 */
@Component
public class MediaImageMetadata {
	public static String getImageSpecString(InputStream inputStream) throws IOException, ImageProcessingException {
		Metadata metadata = ImageMetadataReader.readMetadata(inputStream);

		ExifIFD0Directory exifIFD0Directory = metadata.getFirstDirectoryOfType(ExifIFD0Directory.class);
		ExifIFD0Descriptor exifIFD0Descriptor = new ExifIFD0Descriptor(exifIFD0Directory);
		ExifSubIFDDirectory exifSubIFDDirectory = metadata.getFirstDirectoryOfType(ExifSubIFDDirectory.class);
		ExifSubIFDDescriptor exifSubIFDDescriptor = new ExifSubIFDDescriptor(exifSubIFDDirectory);

		if (exifIFD0Directory != null && exifSubIFDDirectory != null) {
			return exifIFD0Descriptor.getDescription(exifIFD0Directory.TAG_MAKE) + " "
					+ exifIFD0Descriptor.getDescription(exifIFD0Directory.TAG_MODEL) + " ("
					+ exifSubIFDDescriptor.getLensSpecificationDescription() + ") / ISO "
					+ exifSubIFDDescriptor.getIsoEquivalentDescription() + ", "
					+ exifSubIFDDescriptor.getExposureTimeDescription() + ", "
					+ exifSubIFDDescriptor.getFNumberDescription();
		} else {
			return null;
		}
	}
}
