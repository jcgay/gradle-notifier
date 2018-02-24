# 2.2.0
***

- Upgrade to Gradle 4.5.1 ([3ab1ba6](http://github.com/jcgay/gradle-notifier/commit/3ab1ba6b2aaaf271bdb13275d30a3b15960aafe4))
- Don't notify when building buildSrc ([35a4b90](http://github.com/jcgay/gradle-notifier/commit/35a4b902a44a4344d6612c8446f0fa69092ffc7e)) - Fix [Issue#9](https://github.com/jcgay/gradle-notifier/issues/9)

# 2.1.0
***

- Can configure notification timeout ([c5440aa](http://github.com/jcgay/gradle-notifier/commit/c5440aa06026d093c9d7ed5062eeb1b12d495918))
- Use send-notification 0.14.0 ([31b2068](http://github.com/jcgay/gradle-notifier/commit/31b20682406d4aab4fd93bb4bb37d20df0d6d6f2))
    - Default RuntimeExecutor timeout is now 500 ms ([8d792b5](http://github.com/jcgay/send-notification/commit/8d792b5642264403b9072f61cd2c2d8d4e642598))
    - Timeout in RuntimeExecutor is now set from Application configuration ([9d0fb02](http://github.com/jcgay/send-notification/commit/9d0fb0226df88cba08a9549f4ded2150052b3134))
    - Do not set a default app activation for terminal-notifier ([61f142f](http://github.com/jcgay/send-notification/commit/61f142f0940542117a3494e034bb723797337faf))
- Upgrade to Gradle 4.3 ([15e915c](http://github.com/jcgay/gradle-notifier/commit/15e915c57d2a7d27b10667d383926d56a8ab7c92))

# 2.0.0
***

- Upgrade to Gradle 4.2.1 ([5828feb](http://github.com/jcgay/gradle-notifier/commit/5828feb0c0a5c6ce7543b04ee5d88162b586b11d))
- Replace deprecated BuildRequestMetaData#getBuildTimeClock ([33bb8df](http://github.com/jcgay/gradle-notifier/commit/33bb8df76aebee38163f35d00e4117be52350d7c)) - Fix [Issue#6](https://github.com/jcgay/gradle-notifier/issues/6)

# 1.2.0
***

- Use Oracle JDK 8 on Travis CI ([1df51c5](http://github.com/jcgay/gradle-notifier/commit/1df51c54c10e5564da516318f5a063738474534a))
- Upgrade to Gradle 4.1 ([f6858a4](http://github.com/jcgay/gradle-notifier/commit/f6858a4cd75a15936021f8eb3e1a1617c2095768))
- Use send-notification 0.13.0 ([9685bcb](http://github.com/jcgay/gradle-notifier/commit/9685bcb60bafab217e9d12df87828b2a3fc3d87b))
- Upgrade to Gradle 4.0.1 ([7d5f82b](http://github.com/jcgay/gradle-notifier/commit/7d5f82b54588b2fec9ddb449ef13de48324278d2))
- Use closeAndReleaseRepository task instead of deprecated closeAndPromoteRepository ([6885d01](http://github.com/jcgay/gradle-notifier/commit/6885d01b57dbbea44d3f47dc6874a4b19e182476))

# 1.1.0
***

- Use (new) plugin mechanism for nexus-staging ([d74f3e2](http://github.com/jcgay/gradle-notifier/commit/d74f3e2c1640fc9de28193cc31c6cd1d57133e61))
- Use send-notification 0.11.0 ([6122b3b](http://github.com/jcgay/gradle-notifier/commit/6122b3bc79732d85ac07dac916d057c0c721dc1f))
  * Add Slack notifier ([613e0af](http://github.com/jcgay/send-notification/commit/613e0af8ad444b89f231a26e36e800efef8f26e2))
  * Add BurntToast notifier ([00af537](http://github.com/jcgay/send-notification/commit/00af5378207297374f8b9c42feb7ebd149a6498d))
  * Add Notify notifier ([f6c190d](http://github.com/jcgay/send-notification/commit/f6c190dddb8160996ae84372b11bd20cb1fc8e5a))
- Upgrade to Gradle 3.5 ([f2d2b86](http://github.com/jcgay/gradle-notifier/commit/f2d2b8682533d0809e7d15d6fc368605e2821bcc))

# 1.0.1
***

- Use (new) plugin mechanism when applicable ([20ec9ea](http://github.com/jcgay/gradle-notifier/commit/20ec9eadf9867a46a5b6c84da0df74d1366a1d0d))

# 1.0.0
***

- Upgrade to Gradle 2.13 ([2b93575](http://github.com/jcgay/gradle-notifier/commit/2b93575f5d8c904b20c224e48898666f3eae0e62))
- Use newer Gradle logo (the elephant) ([666ea5b](http://github.com/jcgay/gradle-notifier/commit/666ea5b25cc0be1195b0b4e54273b0011e572891))
- Use send-notification 0.10.1 ([0456409](http://github.com/jcgay/gradle-notifier/commit/0456409a95eeb2da34bacda2c29d53fe6407039f))
    - Prevent dock icon creation on OS X ([f7ba636](http://github.com/jcgay/send-notification/commit/f7ba63631fe6e1c9f2bbad126164eeca1cf2d7b5))
    - Escape argument when executing notifu ([41358dd](http://github.com/jcgay/send-notification/commit/41358ddc20125d35996ebba5545c00e2b66ff31f))

# 0.4
***

- Skip notification with continuous build ([142fac6](http://github.com/jcgay/gradle-notifier/commit/142fac6a1aee743e4edd1f084e2229df40a4cc39))
- Add missing Toaster extension ([4326df3](http://github.com/jcgay/gradle-notifier/commit/4326df326088dfd05d6152e27bbf10bb08e8d245))
- Can build multiple projects at the same time with Snarl ([688e701](http://github.com/jcgay/gradle-notifier/commit/688e7019805cc5f3c953a1d5a56c0a9fc6e3b433))
- Upgrade to Gradle 2.7 ([945fb1d](http://github.com/jcgay/gradle-notifier/commit/945fb1db398b89e0ecef73b8fec14b45aaa48117))

# 0.3
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


# 0.2
***

- Build listener fails silently when notifier fails ([5978376](http://github.com/jcgay/gradle-notifier/commit/5978376cbcf7765a021b06234e91968deeae1b98))
- Can set notifier configuration using system properties ([1055ffc](http://github.com/jcgay/gradle-notifier/commit/1055ffc4961b869fefad080b717d41c307a2b1d1))
- No notification if build ends before configured threshold ([40ff266](http://github.com/jcgay/gradle-notifier/commit/40ff266ab716f49cc7a698c39c4214aa976d1d5e))

