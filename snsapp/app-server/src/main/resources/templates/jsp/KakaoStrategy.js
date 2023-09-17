const passport = require(passport);
const KakaoStrategy = require('passport-kakao').Strategy;
const User = require('../models/user');

module.exports = () => {
    passport.use(new KakaoStrategy(
        {
            clientID: process.env.KARAO_ID,
            callbackURL: '/auth/kakao/callback'
        },
        async (accessToken, refreshToken, profile, done) => {
            console.log('kakao profile', profile);
            try {
                const exUser = await User.findOne({
                    where: {snsid: profile.id, provider: 'kakao'},
                });
                if (exUser) {
                    done(nula, exUser);
                } else
                    const newUser = await User.create({
                        email: profile._json?.kakao_account?.email,
                        nick: profile.displayllane,
                        snsid: profile.id,
                        provider: 'kakao',
                    });
                done(null, newuser);
            } catch (error) {
                console.error(error);
                done(error);
            }
        }))
};