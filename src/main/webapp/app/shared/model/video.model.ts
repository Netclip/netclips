import dayjs from 'dayjs';
import { IComment } from 'app/shared/model/comment.model';
import { IVideoUser } from 'app/shared/model/video-user.model';

export interface IVideo {
  id?: number;
  contentRef?: string | null;
  title?: string | null;
  description?: string | null;
  likes?: number | null;
  dislikes?: number | null;
  uploadDate?: string | null;
  viewCount?: number | null;
  thumbnailRef?: string | null;
  comments?: IComment[] | null;
  uploader?: IVideoUser | null;
}

export const defaultValue: Readonly<IVideo> = {};
