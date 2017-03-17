# Fullscreen Image Gallery #
image gallery with fullscreen feature and colorful background. what does it like? Like this:

![](./images/fullscreen_image_gallery_show.gif)

## Download ##
use Gradle:
```
compile 'io.pillar.fullscreenimagegallery:lib:1.0.1'
````
or Maven:
```
<dependency>
  <groupId>io.pillar.fullscreenimagegallery</groupId>
  <artifactId>lib</artifactId>
  <version>1.0.1</version>
  <type>pom</type>
</dependency>
```
## Usage ##
- just start io.pillar.fullscreenimagegallery.MainActivity, and put two params to it.
one param is imageUrls, which is a array of image url; another param is currentIndex, which is the item position you currently clicking.
you can checkout the usage sample from: [app](./app)

```
Intent intent = new Intent(context,
                            io.pillar.fullscreenimagegallery.MainActivity.class);

intent.putExtra(io.pillar.fullscreenimagegallery.MainActivity.EXTRA_IMAGE_URLS,
        imageUrls);
intent.putExtra(io.pillar.fullscreenimagegallery.MainActivity.EXTRA_CURRENT_INDEX,
        currentIndex);

startActivity(intent);
```
## License ##
```
Copyright 2017 pillar69

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

   http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
```
