[![GitHub license](https://img.shields.io/github/license/DubbaDing/JIMMan?color=m&style=for-the-badge)](https://github.com/DubbaDing/JIMMan/blob/master/LICENSE)    [![GitHub issues](https://img.shields.io/github/issues/DubbaDing/JIMMan?style=for-the-badge)](https://github.com/DubbaDing/JIMMan/issues)    ![Maintenance](https://img.shields.io/maintenance/yes/2020?style=for-the-badge)

# JIMMan
**J**ava **IM**Age **MAN**ipulator <br>
This is a simplified software to manipulate images. It can be run on the terminal with arguments or on the terminal with menus. Images are exported as a sibling to the selected image. All outputs are in jpg.

Checkout [YouTube](https://www.youtube.com/watch?v=PTxw9QElORk) for a demo!

## Description
This program uses the Java ImageIO library to manipulate the pixels of an image

## Install
Git Clone: 
```bash
git clone https://github.com/DubbaDing/JIMMan.git
```
Compile:
```bash
$ cd JIMMan/src/jimmman
$ javac JIMMan.java
$ java JIMMan.java
```

## Usage
CLI:
```bash
$ java -jar JIMMan [image] [command]
```
CLI with Menus:
```bash
$ java -jar JIMMan
```

### Images
Type the full or relative path to an image

### Available Commands
- ` invert `      Invert the image's colors
- ` greyscale `   Change the image's colors to greyscale
- ` red `         Only display the red colors
- ` green `       Only display the green colors
- ` blue `        Only display the blue colors
- ` distorted `   Messing with the pixels to make distortion
- ` mixup `       Mix up the red, green, blue pixels

### Output
The output will be in the same location as the original file. The file name will be the same, except with "_" and the command used. All outputs will be in .jpg format.

## TODO
Feel free to fork me and put in a pull request!

- Add more mans (manipulators)
- Allow for alternative export formats (.png, .jpeg)
- Allow for choosing the output path/name
- Create a release and installer

## License
[![GitHub license](https://img.shields.io/github/license/DubbaDing/JIMMan?color=m&style=for-the-badge)](https://github.com/DubbaDing/JIMMan/blob/master/LICENSE)

MIT License

Copyright (c) 2020 Carl Caldwell

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.
