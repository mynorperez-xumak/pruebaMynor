Feature: US-141 Product Media
  The media player component contains a video player for brightcove videos and an audio player for scene7 audio files.
  The products doesn't necessarily have to contain media files related to it, so the players should be rendered if the product do have media.

   Scenario: Product media is obtained
      Given the visitor has opened a product page
      And the product has media related to id
      And the videos URLs contained on the product data are from brightcove
      And the audio URLs contained on the product data are from scene7
      When the video player renders the videos list
      And the audio player renders the audio files list
      Then the visitor can play the videos on the video player
      And the visitor can play the audio files on the audio player