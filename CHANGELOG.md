#1.0.1
***

- Use (new) plugin mechanism when applicable ([20ec9ea](http://github.com/jcgay/gradle-notifier/commit/20ec9eadf9867a46a5b6c84da0df74d1366a1d0d))

#1.0.0
***

- Upgrade to Gradle 2.13 ([2b93575](http://github.com/jcgay/gradle-notifier/commit/2b93575f5d8c904b20c224e48898666f3eae0e62))
- Use newer Gradle logo (the elephant) ([666ea5b](http://github.com/jcgay/gradle-notifier/commit/666ea5b25cc0be1195b0b4e54273b0011e572891))
- Use send-notification 0.10.1 ([0456409](http://github.com/jcgay/gradle-notifier/commit/0456409a95eeb2da34bacda2c29d53fe6407039f))
    - Prevent dock icon creation on OS X ([f7ba636](http://github.com/jcgay/send-notification/commit/f7ba63631fe6e1c9f2bbad126164eeca1cf2d7b5))
    - Escape argument when executing notifu ([41358dd](http://github.com/jcgay/send-notification/commit/41358ddc20125d35996ebba5545c00e2b66ff31f))

#0.4
***

- Skip notification with continuous build ([142fac6](http://github.com/jcgay/gradle-notifier/commit/142fac6a1aee743e4edd1f084e2229df40a4cc39))
- Add missing Toaster extension ([4326df3](http://github.com/jcgay/gradle-notifier/commit/4326df326088dfd05d6152e27bbf10bb08e8d245))
- Can build multiple projects at the same time with Snarl ([688e701](http://github.com/jcgay/gradle-notifier/commit/688e7019805cc5f3c953a1d5a56c0a9fc6e3b433))
- Upgrade to Gradle 2.7 ([945fb1d](http://github.com/jcgay/gradle-notifier/commit/945fb1db398b89e0ecef73b8fec14b45aaa48117))

#0.3
***

- Use internal Gradle clock to display build time ([cd20bfa](http://github.com/jcgay/gradle-notifier/commit/cd20bfa4cfd27f8c2f7721fb12670d750a675c55))
- Always send notification when a notifier is persistent ([29bfab2](http://github.com/jcgay/gradle-notifier/commit/29bfab2398bac4a3bea9d2624f7198053680625d))
- Display 'Build Failed.' when failure message is `null` ([f32bd1a](http://github.com/jcgay/gradle-notifier/commit/f32bd1aa5f04dbef68b7518807dbc36ba2496756))
- Use application icon with terminal-notifier ([3e6e6bb](http://github.com/jcgay/gradle-notifier/commit/3e6e6bb35356292ab44009826814498a7810cd7a))
- Add [toaster](https://github.com/nels-o/toaster) notifier ([3e6e6bb](http://github.com/jcgay/gradle-notifier/commit/3e6e6bb35356292ab44009826814498a7810cd7a))
- Can use Apple notification center with AppleScript ([3e6e6bb](http://github.com/jcgay/gradle-notifier/commit/3e6e6bb35356292ab44009826814498a7810cd7a))
- Automatically choose a notifier when no implementation is provided ([3e6e6bb](http://github.com/jcgay/gradle-notifier/commit/3e6e6bb35356292ab44009826814498a7810cd7a))
- Can send notification to multiple implementations at once ([3e6e6bb](http://github.com/jcgay/gradle-notifier/commit/3e6e6bb35356292ab44009826814498a7810cd7a))
- Set `notifier.implementation` to `none` to not send notification ([3e6e6bb](http://github.com/jcgay/gradle-notifier/commit/3e6e6bb35356292ab44009826814498a7810cd7a))


#0.2
***

- Build listener fails silently when notifier fails ([5978376](http://github.com/jcgay/gradle-notifier/commit/5978376cbcf7765a021b06234e91968deeae1b98))
- Can set notifier configuration using system properties ([1055ffc](http://github.com/jcgay/gradle-notifier/commit/1055ffc4961b869fefad080b717d41c307a2b1d1))
- No notification if build ends before configured threshold ([40ff266](http://github.com/jcgay/gradle-notifier/commit/40ff266ab716f49cc7a698c39c4214aa976d1d5e))

