import { IUser } from 'app/shared/model/user.model';
import { IComment } from 'app/shared/model/comment.model';
import { IVideo } from 'app/shared/model/video.model';

export interface IVideoUser {
  id?: number;
  internalUser?: IUser | null;
  comments?: IComment[] | null;
  videos?: IVideo[] | null;
  likedVideos?: IVideo[] | null;
  videosDisliked?: IVideo[] | null;
  likedComments?: IComment[] | null;
  dislikedComments?: IComment[] | null;
}

export const defaultValue: Readonly<IVideoUser> = {};
