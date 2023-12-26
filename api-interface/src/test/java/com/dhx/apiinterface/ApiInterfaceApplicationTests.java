package com.dhx.apiinterface;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.dhx.apiinterface.domain.ComputerSuffix;
import com.dhx.apiinterface.mapper.ComputerSuffixMapper;
import com.dhx.apiinterface.service.ComputerSuffixService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

@SpringBootTest
class ApiInterfaceApplicationTests {

    public void adddSuffix(){
        String str= "图像文件\n" +
                ".ani：Windows 动画文件格式。ANI 文件是一种 Windows 动画文件格式，可以用于创建动画图标和光标。\n" +
                ".art：PFS:First Publisher 艺术文件格式。ART 文件是一种 PFS:First Publisher 艺术文件格式，用于图形和排版设计。\n" +
                ".avif：AV1 视频文件格式。AVIF 是一种基于 AV1 视频编解码器的图像文件格式，具有更好的压缩性能和更高的图像质量。\n" +
                ".cdr：CorelDRAW 图形文件格式。CDR 文件是一种 CorelDRAW 图形文件格式，用于存储矢量图形和位图图像。\n" +
                ".cgm：计算机图形元文件格式。CGM 文件是一种计算机图形元文件格式，用于存储 2D 矢量图形。\n" +
                ".djv、.djvu：DjVu 文件格式。DjVu 文件是一种用于存储扫描文档和图像的文件格式，具有高压缩比和高质量输出的特点。\n" +
                ".eps：Encapsulated PostScript 文件格式。EPS 文件是一种用于存储矢量图形的文件格式，可以在多个应用程序中使用。\n" +
                ".exif：Exchangeable Image File Format 文件格式。EXIF 文件是一种用于存储数字照片和图像的文件格式，包括照片的元数据信息，如拍摄日期、相机型号、ISO 等。\n" +
                ".fh、.fhc、.fh4、.fh5、.fh7：FreeHand 文件格式。FreeHand 文件是一种矢量图形文件格式，用于存储图形设计和排版文档。\n" +
                ".gif：GIF 动画文件格式。GIF 文件是一种用于存储动画图像的文件格式，支持无限循环、透明度和动画帧率设置。\n" +
                ".hdr：Radiance RGBE 文件格式。HDR 文件是一种 Radiance RGBE 文件格式，用于存储高动态范围图像。\n" +
                ".ico：Windows 图标文件格式。ICO 文件是一种 Windows 图标文件格式，用于存储图标和图标集。\n" +
                ".iff：Interchange 文件格式。IFF 文件是一种 Interchange 文件格式，用于存储多媒体数据，如音频、图像和动画。\n" +
                ".jfif、.jpe、.jpeg、.jpg：Joint Photographic Experts Group 文件格式。JPEG 文件是一种用于存储数字照片和图像的文件格式，具有高压缩比和高质量输出的特点。\n" +
                ".jp2、.jpf、.jpx、.j2k、.j2c：JPEG 2000 文件格式。JPEG 2000 文件是一种用于存储数字照片和图像的文件格式，具有更好的压缩性能和更高的图像质量。\n" +
                ".mac：MacPaint 图形文件格式。MAC 文件是一种 MacPaint 图形文件格式，用于存储 1-bit 位图图像。\n" +
                ".pct：Macintosh PICT 图形文件格式。PICT 文件是一种 Macintosh PICT 图形文件格式，用于存储矢量图形和位图图像。\n" +
                ".pdf：Portable Document Format 文件格式。PDF 文件是一种用于存储文档和图像的文件格式，具有可读性和可编辑性的特点。\n" +
                ".pgm：Portable Gray Map 文件格式。PGM 文件是一种用于存储灰度图像的文件格式。\n" +
                ".png：Portable Network Graphics 文件格式。PNG 文件是一种用于存储数字照片和图像的文件格式，支持透明度和多层图像。\n" +
                ".ppm：Portable Pixmap 文件格式。PPM 文件是一种用于存储像素图像的文件格式。\n" +
                ".psd：Photoshop 文档文件格式。PSD 文件是一种 Photoshop 文档文件格式，用于存储矢量图形和位图图像，支持多层图像和图形编辑。\n" +
                ".svg：Scalable Vector Graphics 文件格式。SVG 文件是一种用于存储矢量图形的文件格式，具有可缩放性和可编辑性的特点。\n" +
                ".tga、.tpic：Targa 图形文件格式。TGA 文件是一种 Targa 图形文件格式，用于存储位图图像和 24-bit RGB 图像。\n" +
                ".tif、.tiff：Tagged Image File Format 文件格式。TIFF 文件是一种用于存储数字照片和图像的文件格式，支持多页图像和多种图像压缩方式。\n" +
                ".wbmp：Wireless Bitmap 文件格式。WBMP 文件是一种用于存储无线设备上的位图图像的文件格式，支持黑白图像和灰度图像。\n" +
                ".webp：WebP 图像文件格式。WebP 文件是一种基于 VP8 视频编解码器的图像文件格式，具有更好的压缩性能和更高的图像质量。\n" +
                "音频文件\n" +
                ".aif、.aiff：Audio Interchange File Format 文件格式。AIFF 文件是一种音频文件格式，用于存储无损音频数据。\n" +
                ".au、.snd：Sun/NeXT 音频文件格式。AU 文件是一种 Sun/NeXT 音频文件格式，用于存储音频数据。\n" +
                ".flac：Free Lossless Audio Codec 文件格式。FLAC 文件是一种无损音频文件格式，具有更好的压缩性能和更高的音频质量。\n" +
                ".m4a、.m4b、.m4p、.m4r、.m4v：MPEG-4 音频/视频文件格式。MPEG-4 文件是一种音频/视频文件格式，用于存储数字音频和视频数据。\n" +
                ".mid、.midi：Musical Instrument Digital Interface 文件格式。MIDI 文件是一种音频文件格式，用于存储音乐合成器生成的数字音频数据。\n" +
                ".mp3：MPEG-1 Audio Layer 3 文件格式。MP3 文件是一种音频文件格式，具有更好的压缩性能和更高的音频质量。\n" +
                ".ogg、.oga、.opus：Ogg 文件格式。OGG 文件是一种音频文件格式，具有更好的压缩性能和更高的音频质量。\n" +
                ".wav：Waveform Audio File Format 文件格式。WAV 文件是一种音频文件格式，用于存储数字音频数据。\n" +
                "视频文件\n" +
                ".3gp、.3g2：3GPP 文件格式。3GP 文件是一种用于存储手机视频的文件格式，支持 H.264 和 MPEG-4 编码。\n" +
                ".avi：Audio Video Interleave 文件格式。AVI 文件是一种常用的视频文件格式，支持多种视频编码和音频编码。\n" +
                ".flv：Flash 视频文件格式。FLV 文件是一种 Flash 视频文件格式，用于存储网络视频数据。\n" +
                ".m4v：MPEG-4 视频文件格式。M4V 文件是一种 MPEG-4 视频文件格式，用于存储数字视频数据。\n" +
                ".mkv、.webm：Matroska 文件格式。MKV 文件是一种 Matroska 文件格式，用于存储数字视频和音频数据。\n" +
                ".mov、.qt：QuickTime 文件格式。MOV 文件是一种 QuickTime 文件格式，用于存储数字视频和音频数据。\n" +
                ".mp4、.mpeg4：MPEG-4 文件格式。MP4 文件是一种 MPEG-4 文件格式，用于存储数字视频和音频数据。\n" +
                ".mpg、.mpeg：MPEG 文件格式。MPEG 文件是一种视频文件格式，用于存储数字视频和音频数据。\n" +
                ".rm、.rmvb：RealMedia 文件格式。RM 文件是一种 RealMedia 文件格式，用于存储数字视频和音频数据。";
        String[] split = str.split("\n+");
        String category="";
        int cnt =0 ;
        for (String s : split) {
            if(!s.startsWith(".")){
                category=s;
                continue;
            }
            int i = s.indexOf("：");
            // 描述
            String desc = s.substring(i+1);
            String suffixs= s.substring(0,i);
            String[] suffix = suffixs.split("、");
            for (String suffix1 : suffix) {
                ComputerSuffix computerSuffix = new ComputerSuffix();
                computerSuffix.setSuffix(suffix1);
                computerSuffix.setDescription(desc);
                computerSuffix.setCategory(category);
                cnt++;
                if(computerSuffixMapper.selectCount(new QueryWrapper<ComputerSuffix>().eq("suffix",computerSuffix.getSuffix()))!=0){
                    computerSuffixMapper.updateById(computerSuffix);
                    System.out.println("已经存在了 ");
                    continue;
                }
                computerSuffixMapper.insert(computerSuffix);
                System.out.println(computerSuffix.getSuffix());
//                System.out.println(computerSuffix);
            }
        }
        System.out.println("=========total: "+cnt);
    }

    @Resource
    ComputerSuffixMapper computerSuffixMapper;

    @Resource
    ComputerSuffixService service;
}
